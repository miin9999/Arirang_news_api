package diy.arirangnewsapi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import diy.arirangnewsapi.data.db.dao.NewsDao
import diy.arirangnewsapi.data.entity.NewsDetailEntity


@Database(
    entities =[NewsDetailEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ApplicationDatabase:RoomDatabase() {

    companion object{
        const val DB_NAME = "ApplicationDataBase.db"

    }

    abstract fun NewsDao(): NewsDao


}