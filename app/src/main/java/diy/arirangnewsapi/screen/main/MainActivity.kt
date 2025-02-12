package diy.arirangnewsapi.screen.main

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.appcompat.view.ActionMode
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import diy.arirangnewsapi.R
import diy.arirangnewsapi.databinding.ActivityMainBinding
import diy.arirangnewsapi.screen.main.setting.SettingFragment
import diy.arirangnewsapi.screen.main.home.HomeFragment
import diy.arirangnewsapi.screen.main.myword.MyWordFragment
import diy.arirangnewsapi.screen.main.profile.TodayWordFragment
import diy.arirangnewsapi.screen.main.scrap.ScrapFragment
import diy.arirangnewsapi.screen.main.scrap.SharedViewModel
import diy.arirangnewsapi.util.data_update_receiver.DataUpdateReceiver

import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar
import java.util.TimeZone


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
        setupDailyAlarm()


    }
    @SuppressLint("ScheduleExactAlarm")
    private fun setupDailyAlarm() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, DataUpdateReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // 한국 시간 기준 00:00 설정
        val calendar = Calendar.getInstance().apply {
            timeZone = TimeZone.getTimeZone("Asia/Seoul") // 한국 시간대
            set(Calendar.HOUR_OF_DAY, 0) // 00시
            set(Calendar.MINUTE, 0)     // 00분
            set(Calendar.SECOND, 0)     // 00초
            set(Calendar.MILLISECOND, 0)

            // 현재 시간이 이미 자정을 지난 경우, 다음 날로 설정
            if (timeInMillis <= System.currentTimeMillis()) {
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }

        val triggerAtMillis = System.currentTimeMillis() + 60 * 1000 // 1분 단위 테스트 용, 이걸 calendar.timeInMillis 대신 넣으면 됨
        // 정확한 시간에 알람 실행
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )

        Log.d("alarm Main", "Daily alarm set for ${calendar.time}")
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        actionMode?.finish()

        return when(item.itemId){
            R.id.menu_home ->{
                showFragment(HomeFragment.newInstance(),HomeFragment.TAG)
                true
            }
            R.id.menu_scrab ->{
                showFragment(ScrapFragment.newInstance(),ScrapFragment.TAG)
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

            R.id.menu_setting ->{
                showFragment(SettingFragment.newInstance(),SettingFragment.TAG)

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