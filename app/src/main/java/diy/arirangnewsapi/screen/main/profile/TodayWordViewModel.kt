package diy.arirangnewsapi.screen.main.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import diy.arirangnewsapi.data.db.dao.WordDao
import diy.arirangnewsapi.data.entity.WordEntity
import diy.arirangnewsapi.data.repository.Translation.WordRepository
import diy.arirangnewsapi.screen.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TodayWordViewModel(
    private val ioDispatcher: CoroutineDispatcher,
    private val wordRepository: WordRepository
): BaseViewModel() {

    private val _data = MutableLiveData<WordEntity?>()
    val data: LiveData<WordEntity?> = _data

    suspend fun fetchDataFromRoom(){

        Log.d("alarm TodayViewModel","starts")

        withContext(ioDispatcher) {
            val newData = wordRepository.getRandomOneWord()
            Log.d("alarm TodayViewModel",newData.toString())

            _data.postValue(newData)

            Log.d("alarm TodayViewModel",data.toString())

        }


    }


}