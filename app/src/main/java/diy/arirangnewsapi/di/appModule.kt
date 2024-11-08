package diy.arirangnewsapi.di

import diy.arirangnewsapi.ArirangApplication.Companion.appContext
import diy.arirangnewsapi.data.entity.NewsDetailEntity
import diy.arirangnewsapi.data.repository.News.DefaultNewsRepository
import diy.arirangnewsapi.data.repository.News.NewsRepository
import diy.arirangnewsapi.screen.main.home.HomeViewModel
import diy.arirangnewsapi.screen.main.home.detail.NewsDetailViewModel
import diy.arirangnewsapi.screen.main.myword.MyWordViewModel
import diy.arirangnewsapi.screen.main.profile.ProfileViewModel
import diy.arirangnewsapi.screen.main.scrab.ScrabViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{

    viewModel{ HomeViewModel(get())}
    viewModel{ MyWordViewModel()}
    viewModel{ ProfileViewModel()}
    viewModel{ ScrabViewModel()}
    viewModel{ (newsDetailEntity:NewsDetailEntity) -> NewsDetailViewModel(newsDetailEntity,get()) }


    single<NewsRepository>{ DefaultNewsRepository(get(),get(),get()) }



    single{ provideGsonConvertFactory() }
    single{ buildOkHttpClient() }
    single { provideNewsRetrofit(get(),get()) }


    single{ provideNewsApiService(get()) }



    single{ provideDB(androidApplication()) }
    single { provideNewsDao(get()) }




    single{ Dispatchers.IO}
    single{ Dispatchers.Main}


}