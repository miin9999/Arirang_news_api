package diy.arirangnewsapi.data.repository.Translation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import diy.arirangnewsapi.data.entity.WordEntity

interface WordRepository {

    suspend fun insertWord(text:WordEntity):Long

    fun getAllWord():LiveData<List<WordEntity?>>

    suspend fun deleteSelectedWords(id: List<Long?>)

    fun getWordCount(): LiveData<Int>

}