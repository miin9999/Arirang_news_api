package diy.arirangnewsapi.screen.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
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
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(),BottomNavigationView.OnNavigationItemSelectedListener{


    private lateinit var binding: ActivityMainBinding

    private val sharedViewModel: SharedViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()


    }


    fun observeData()= sharedViewModel.isBottomNavVisible.observe(this@MainActivity){

        if(it==false){
            binding.bottomNav.visibility = View.GONE
            binding.popupButtonContainer.visibility = View.VISIBLE
        } else {
            // 취소,삭제 버튼을 누를 시 버튼 원상복구
            binding.bottomNav.visibility = View.VISIBLE
            binding.popupButtonContainer.visibility = View.GONE

        }


    }


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
        observeData()

        deleteButton.setOnClickListener{
            lifecycleScope.launch {
                sharedViewModel.deleteSelectedItems()
            }
            sharedViewModel.toggleRadioAndBottomButtonsVisibility()

        }

        cancelButton.setOnClickListener {
            sharedViewModel.toggleRadioAndBottomButtonsVisibility()
        }




    }


}

enum class MainTabMenu(@IdRes val menuId: Int) {
    HOME(R.id.menu_home), SCRAP(R.id.menu_scrab), MYWORD(R.id.menu_myword), PROFILE(R.id.menu_profile)
}