package diy.arirangnewsapi.util.data_update_receiver

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import diy.arirangnewsapi.data.db.dao.WordDao
import diy.arirangnewsapi.screen.main.profile.TodayWordViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.getKoin
import org.koin.java.KoinJavaComponent.inject
import java.util.Calendar
import java.util.TimeZone

class DataUpdateReceiver : BroadcastReceiver() {


    @SuppressLint("ScheduleExactAlarm")
    override fun onReceive(context: Context, intent: Intent?) {
        Log.d("alarmReceiver", "Daily alarm triggered")

        val viewModel = getKoin().get<TodayWordViewModel>()
        val wordDao = getKoin().get<WordDao>()
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

        CoroutineScope(Dispatchers.IO).launch {
            val newData = wordDao.getOneWord()

            Log.d("CoroutineScope", newData.toString())
            sharedPreferences.edit()
                .putString(ORIGINAL_KEY, newData?.originalWord)
                .putString(TRANSLATED_KEY, newData?.translatedWord)
                .apply()

            // 다음 날 자정을 위한 알람 재설정
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val newIntent = Intent(context, DataUpdateReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                newIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            val calendar = Calendar.getInstance().apply {
                timeZone = TimeZone.getTimeZone("Asia/Seoul")
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
                add(Calendar.DAY_OF_YEAR, 1) // 다음 날
            }

            //val triggerAtMillis = System.currentTimeMillis() + 60 * 1000 // 1분 단위 테스트 용


            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )
        }
    }


    companion object{
        const val ORIGINAL_KEY = "ORIGINAL_WORD_KEY"
        const val TRANSLATED_KEY = "TRANSLATED_WORD_KEY"

    }
}



