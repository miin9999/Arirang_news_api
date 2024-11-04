package diy.arirangnewsapi

import android.app.Application
import android.content.Context
import diy.arirangnewsapi.di.appModule
import org.koin.core.context.startKoin

class ArirangApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this

        startKoin{

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