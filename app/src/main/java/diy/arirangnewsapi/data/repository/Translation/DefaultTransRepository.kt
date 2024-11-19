package diy.arirangnewsapi.data.repository.Translation


import androidx.lifecycle.LiveData
import com.google.cloud.translate.Translate
import diy.arirangnewsapi.data.db.dao.WordDao
import diy.arirangnewsapi.data.entity.WordEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext


class DefaultTransRepository(
    private val ioDispatcher: CoroutineDispatcher,
    private val wordDao: WordDao
):TransRepository {


    override suspend fun insertWord(text: WordEntity):Long = withContext(ioDispatcher){
        wordDao.insertWord(text)
    }

    override fun getAllWord(): LiveData<List<WordEntity?>> = wordDao.getAllWords()
    //override fun getAllWord(): LiveData<List<WordEntity?>> = wordDao.getAllWords()


}