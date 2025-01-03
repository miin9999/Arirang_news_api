package diy.arirangnewsapi.screen.main

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.appcompat.view.ActionMode
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import diy.arirangnewsapi.R
import diy.arirangnewsapi.databinding.ActivityMainBinding
import diy.arirangnewsapi.screen.main.home.HomeFragment
import diy.arirangnewsapi.screen.main.myword.MyWordFragment
import diy.arirangnewsapi.screen.main.profile.TodayWordFragment
import diy.arirangnewsapi.screen.main.scrab.ScrabFragment
import diy.arirangnewsapi.screen.main.scrab.SharedViewModel
import diy.arirangnewsapi.util.data_update_receiver.DataUpdateReceiver

import kotlinx.coroutines.launch
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
        setupAlarm()


    }
    private fun setupAlarm() {


        Log.d("alarm Main","setupAlarm")


        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, DataUpdateReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // 15분 간격 반복 (밀리초 단위)
        val interval = 60 * 1000L //15 * 60 * 1000L = 15분
        val triggerAtMillis = SystemClock.elapsedRealtime() + interval

        alarmManager.setRepeating(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            triggerAtMillis,
            interval,
            pendingIntent
        )
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        actionMode?.finish()

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
                showFragment(TodayWordFragment.newInstance(),TodayWordFragment.TAG)

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

        deleteButton.setOnClickListener{
            lifecycleScope.launch {
                sharedViewModel.deleteSelectedNews()
            }
            sharedViewModel.toggleCheckBoxVisibilityOfScrap()

        }

        cancelButton.setOnClickListener {
            sharedViewModel.toggleCheckBoxVisibilityOfScrap()
        }
    }

    fun startActionMode(fragment: Fragment) {
        (fragment as? ActionMode.Callback)?.let { callback ->
            actionMode = startSupportActionMode(callback)
            sharedViewModel.setActionMode(actionMode)

        }
    }





}

enum class MainTabMenu(@IdRes val menuId: Int) {
    HOME(R.id.menu_home), SCRAP(R.id.menu_scrab), MYWORD(R.id.menu_myword), PROFILE(R.id.menu_profile)
}