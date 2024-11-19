package diy.arirangnewsapi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import diy.arirangnewsapi.data.db.dao.NewsDao
import diy.arirangnewsapi.data.db.dao.WordDao
import diy.arirangnewsapi.data.entity.NewsDetailEntity
import diy.arirangnewsapi.data.entity.WordEntity


@Database(
    entities =[NewsDetailEntity::class, WordEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ApplicationDatabase:RoomDatabase() {

    companion object{
        const val DB_NAME = "ApplicationDataBase.db"

    }

    abstract fun NewsDao(): NewsDao
    abstract fun WordDao(): WordDao



}