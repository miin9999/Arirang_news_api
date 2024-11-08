package diy.arirangnewsapi.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import diy.arirangnewsapi.data.entity.NewsDetailEntity


@Dao
interface NewsDao {

    @Query("SELECT * FROM NewsDetailEntity WHERE id=:id")
    suspend fun getOneNews(id: Long): NewsDetailEntity?

    @Query("SELECT * FROM NewsDetailEntity")
    suspend fun getAllNews(): List<NewsDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(newsDetailEntity: NewsDetailEntity)

    @Query("DELETE FROM NewsDetailEntity WHERE id=:id")
    suspend fun deleteOneNews(id: Long)


    @Query("DELETE FROM NewsDetailEntity")
    suspend fun deleteAllNews()
}