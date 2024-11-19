package diy.arirangnewsapi.di

import android.content.Context
import androidx.room.Room
import diy.arirangnewsapi.data.db.ApplicationDatabase

fun provideDB(context: Context): ApplicationDatabase =
    Room.databaseBuilder(context, ApplicationDatabase::class.java, ApplicationDatabase.DB_NAME)
        .fallbackToDestructiveMigration()
        .build()

fun provideNewsDao(database: ApplicationDatabase) = database.NewsDao()
fun provideWordDao(database: ApplicationDatabase) = database.WordDao()