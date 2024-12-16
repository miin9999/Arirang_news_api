package diy.arirangnewsapi.util.data_update_receiver

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

class DataUpdateReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        Log.d("alarm Receiver", "starts")

        val viewModel = getKoin().get<TodayWordViewModel>()
        val wordDao = getKoin().get<WordDao>()

        CoroutineScope(Dispatchers.IO).launch{
            val newData = wordDao.getOneWord()
            val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            Log.d("CoroutineScope",newData.toString())
            sharedPreferences.edit().putString("latest_data", newData?.originalWord).apply()
        }


//        CoroutineScope(Dispatchers.IO).launch {
//            viewModel.fetchDataFromRoom()
//
//        }



    }
}

