package diy.arirangnewsapi.util.workmanager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker

import androidx.work.WorkerParameters
import androidx.work.workDataOf
import diy.arirangnewsapi.data.db.dao.WordDao
import org.koin.java.KoinJavaComponent.getKoin


class FetchWordWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {

        Log.d("do work","do work starts")
        val wordDao = getKoin().get<WordDao>()

        val word = wordDao.getOneWord()?.originalWord
        return if (!word.isNullOrEmpty()) {
            Log.d("do work","word is not empty : ${word}")

            Result.success(workDataOf("word" to word))
        } else {
            Result.failure()
        }
    }
}

