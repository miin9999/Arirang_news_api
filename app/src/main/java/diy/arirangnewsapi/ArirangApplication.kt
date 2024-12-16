package diy.arirangnewsapi

import android.app.Application
import android.content.Context
import android.util.Log
import diy.arirangnewsapi.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class ArirangApplication: Application(){


    override fun onCreate() {
        super.onCreate()

        appContext = this

        startKoin {
            androidContext(this@ArirangApplication)
            androidLogger(Level.DEBUG)
            modules(appModule)
        }

    }



    override fun onTerminate() {
        super.onTerminate()
        appContext = null
    }


    companion object {

        var appContext: Context? = null
            private set

    }





}