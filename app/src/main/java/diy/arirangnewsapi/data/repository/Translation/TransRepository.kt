package diy.arirangnewsapi.data.repository.Translation

import androidx.lifecycle.LiveData
import com.google.cloud.translate.Translate
import diy.arirangnewsapi.data.entity.WordEntity

interface TransRepository {

    suspend fun insertWord(text:WordEntity):Long

    fun getAllWord():LiveData<List<WordEntity?>>

}