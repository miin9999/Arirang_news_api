package diy.arirangnewsapi.screen.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import diy.arirangnewsapi.data.entity.WordEntity
import diy.arirangnewsapi.data.repository.Translation.WordRepository
import diy.arirangnewsapi.screen.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher

class TodayWordViewModel(
    private val ioDispatcher: CoroutineDispatcher,
    private val wordRepository: WordRepository
): BaseViewModel() {

    val wordLiveData =MutableLiveData<WordEntity?>()

    val originalWordLiveData = MutableLiveData<String?>()
    val translatedWordLiveData = MutableLiveData<String?>()



//    suspend fun fetchDataFromRoom(){
//
//        Log.d("alarm TodayViewModel","starts")
//
//        withContext(ioDispatcher) {
//            val newData = wordRepository.getRandomOneWord()
//            Log.d("alarm TodayViewModel",newData.toString())
//
//            _data.postValue(newData)
//
//            Log.d("alarm TodayViewModel",data.toString())
//
//        }
//
//
//    }


}