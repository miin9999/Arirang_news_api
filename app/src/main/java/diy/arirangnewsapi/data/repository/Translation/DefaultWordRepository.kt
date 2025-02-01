package diy.arirangnewsapi.data.repository.Translation


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import diy.arirangnewsapi.data.db.dao.WordDao
import diy.arirangnewsapi.data.entity.WordEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext


class DefaultWordRepository(
    private val ioDispatcher: CoroutineDispatcher,
    private val wordDao: WordDao
):WordRepository {


    override suspend fun insertWord(text: WordEntity):Long = withContext(ioDispatcher){
        wordDao.insertWord(text)
    }

    override fun getAllWord(): LiveData<List<WordEntity?>> = wordDao.getAllWords()


    override suspend fun deleteSelectedWords(id: List<Long?>) = withContext(ioDispatcher){
        wordDao.deleteSelectedNews(id)
    }

    override suspend fun getRandomOneWord(): WordEntity? = withContext(ioDispatcher){
        wordDao.getOneWord()
    }

    override fun getWordCount(): LiveData<Int> = wordDao.getWordCount()


}