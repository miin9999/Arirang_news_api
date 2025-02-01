package diy.arirangnewsapi.data.db.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import diy.arirangnewsapi.data.entity.NewsDetailEntity
import diy.arirangnewsapi.data.entity.WordEntity

@Dao
interface WordDao {

    @Query("SELECT * FROM WordEntity ORDER BY RANDOM() LIMIT 1")
    suspend fun getOneWord(): WordEntity?

    @Query("SELECT * FROM WordEntity")
    fun getAllWords(): LiveData<List<WordEntity?>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWord(wordEntity: WordEntity):Long

    @Query("DELETE FROM WordEntity WHERE id=:id")
    suspend fun deleteOneWord(id:Long)

    @Query("DELETE FROM WordEntity")
    suspend fun deleteAllWord()

    @Query("DELETE FROM WordEntity WHERE id IN (:id)")
    suspend fun deleteSelectedNews(id:List<Long?>)

    @Query("SELECT COUNT(*) FROM WordEntity WHERE id =:id")
    suspend fun isWordScrapped(id: Long): Int

    @Query("SELECT COUNT(*) FROM WordEntity")
    fun getWordCount(): LiveData<Int>


}