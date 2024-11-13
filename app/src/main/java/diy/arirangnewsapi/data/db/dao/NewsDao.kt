package diy.arirangnewsapi.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import diy.arirangnewsapi.data.entity.NewsDetailEntity


@Dao
interface NewsDao {

    @Query("SELECT * FROM NewsDetailEntity WHERE newsUrl=:newsUrl")
    suspend fun getOneNews(newsUrl: Long): NewsDetailEntity?

    @Query("SELECT * FROM NewsDetailEntity")
    fun getAllNews(): LiveData<List<NewsDetailEntity?>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(newsDetailEntity: NewsDetailEntity)

    @Query("DELETE FROM NewsDetailEntity WHERE newsUrl=:newsUrl")
    suspend fun deleteOneNews(newsUrl: String)


    @Query("DELETE FROM NewsDetailEntity")
    suspend fun deleteAllNews()
}