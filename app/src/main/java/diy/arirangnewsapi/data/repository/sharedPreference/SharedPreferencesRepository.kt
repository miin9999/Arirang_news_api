package diy.arirangnewsapi.data.repository.sharedPreference

import android.content.Context
import diy.arirangnewsapi.util.data_update_receiver.DataUpdateReceiver.Companion.ORIGINAL_KEY
import diy.arirangnewsapi.util.data_update_receiver.DataUpdateReceiver.Companion.TRANSLATED_KEY

class SharedPreferencesRepository(private val context: Context) {

    private val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    fun getOriginalWord(): String? {
        return sharedPreferences.getString(ORIGINAL_KEY, null)
    }

    fun saveOriginalWord(word: String) {
        sharedPreferences.edit().putString(ORIGINAL_KEY, word).apply()
    }

    fun removeWordFromSharedPreferences(){
        sharedPreferences.edit().remove(ORIGINAL_KEY).apply()
        sharedPreferences.edit().remove(TRANSLATED_KEY).apply()
    }


}
