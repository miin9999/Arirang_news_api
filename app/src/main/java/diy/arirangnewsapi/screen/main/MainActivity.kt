package diy.arirangnewsapi.screen.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import diy.arirangnewsapi.R
import diy.arirangnewsapi.databinding.ActivityMainBinding
import diy.arirangnewsapi.screen.main.home.HomeFragment
import diy.arirangnewsapi.screen.main.myword.MyWordFragment
import diy.arirangnewsapi.screen.main.profile.ProfileFragment
import diy.arirangnewsapi.screen.main.scrab.ScrabFragment
import diy.arirangnewsapi.screen.main.scrab.SharedViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(),BottomNavigationView.OnNavigationItemSelectedListener{


    private lateinit var binding: ActivityMainBinding

    private val sharedViewModel: SharedViewModel by viewModel()

    private var actionMode: ActionMode? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()



    }


//    fun observeData()= sharedViewModel.isCheckBoxVisible.observe(this@MainActivity){
//
//        if(it==false){
//            binding.bottomNav.visibility = View.GONE
//            binding.popupButtonContainer.visibility = View.VISIBLE
//        } else {
//            // 취소,삭제 버튼을 누를 시 버튼 원상복구
//            binding.bottomNav.visibility = View.VISIBLE
//            binding.popupButtonContainer.visibility = View.GONE
//
//        }
//
//
//    }


    fun initButton()= with(binding){

    }





    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_home ->{
                showFragment(HomeFragment.newInstance(),HomeFragment.TAG)
                true
            }
            R.id.menu_scrab ->{
                showFragment(ScrabFragment.newInstance(),ScrabFragment.TAG)
                true

            }
            R.id.menu_myword ->{
                showFragment(MyWordFragment.newInstance(),MyWordFragment.TAG)

                true
            }
            R.id.menu_profile ->{
                showFragment(ProfileFragment.newInstance(),ProfileFragment.TAG)

                true
            }

            else-> false
        }
    }


    private fun showFragment(fragment: Fragment, tag: String){

        val findFragment = supportFragmentManager.findFragmentByTag(tag)

        supportFragmentManager.fragments.forEach {fm->
            supportFragmentManager.beginTransaction().hide(fm).commitAllowingStateLoss()}


        findFragment?.let{
            supportFragmentManager.beginTransaction().show(it).commitAllowingStateLoss()}
            ?:kotlin.run{
                supportFragmentManager.beginTransaction()
                    .add(R.id.fragmentContainer,fragment, tag)
                    .commitAllowingStateLoss()
            }

    }

    private fun initViews() = with(binding){

        bottomNav.setOnNavigationItemSelectedListener(this@MainActivity)
        showFragment(HomeFragment.newInstance(),HomeFragment.TAG)
        //observeData()

        deleteButton.setOnClickListener{
            lifecycleScope.launch {
                sharedViewModel.deleteSelectedItems()
            }
            sharedViewModel.toggleCheckBoxVisibility()

        }

        cancelButton.setOnClickListener {
            sharedViewModel.toggleCheckBoxVisibility()
        }




    }

    fun startActionMode() {
        actionMode = startActionMode(actionModeCallback)
    }


    private val actionModeCallback = object : ActionMode.Callback {
        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            menu?.add(Menu.NONE, 1, Menu.NONE, "삭제")
            return true
        }

        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            when (item?.itemId) {

                1 -> {
                    // 삭제 버튼 누를 시
                    lifecycleScope.launch {
                        withContext(Dispatchers.IO){
                            sharedViewModel.deleteSelectedItems()
                        }
                    }

                    sharedViewModel.toggleCheckBoxVisibility()
                    mode?.finish()  // 액션 모드 종료
                    return true
                }
            }
            return false
        }

        override fun onDestroyActionMode(mode: ActionMode?) {
            // 액션 모드 종료 시 처리
            sharedViewModel.toggleCheckBoxVisibility()
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            // 액션 모드 준비 시 처리
            return false
        }
    }


}

enum class MainTabMenu(@IdRes val menuId: Int) {
    HOME(R.id.menu_home), SCRAP(R.id.menu_scrab), MYWORD(R.id.menu_myword), PROFILE(R.id.menu_profile)
}