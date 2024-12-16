package diy.arirangnewsapi.util.workmanager


import android.content.Context
import android.util.Log
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import diy.arirangnewsapi.data.db.ApplicationDatabase
import diy.arirangnewsapi.data.db.dao.WordDao
import org.koin.java.KoinJavaComponent.inject
import org.koin.core.context.GlobalContext.get
import org.koin.java.KoinJavaComponent.getKoin

//class CustomWorkerFactory(
//) : WorkerFactory() {
//
//
//
//
//    override fun createWorker(
//        appContext: Context,
//        workerClassName: String,
//        workerParameters: WorkerParameters
//    ): ListenableWorker? {
//
//        return when (workerClassName) {
//            FetchWordWorker::class.java.name -> {
//                Log.d("CustomWorkerFactory", "Injecting WordDao: $wordDao")
//                FetchWordWorker(appContext, workerParameters,wordDao)
//            }
//            else -> null
//        }
//    }
//}

