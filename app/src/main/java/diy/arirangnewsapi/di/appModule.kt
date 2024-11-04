package diy.arirangnewsapi.di

import diy.arirangnewsapi.data.repository.News.DefaultNewsRepository
import diy.arirangnewsapi.data.repository.News.NewsRepository
import diy.arirangnewsapi.screen.main.home.HomeViewModel
import diy.arirangnewsapi.screen.main.myword.MyWordViewModel
import diy.arirangnewsapi.screen.main.profile.ProfileViewModel
import diy.arirangnewsapi.screen.main.scrab.ScrabViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{

    viewModel{ HomeViewModel(get())}
    viewModel{ MyWordViewModel()}
    viewModel{ ProfileViewModel()}
    viewModel{ ScrabViewModel()}


    single<NewsRepository>{ DefaultNewsRepository(get(),get())}



    single{ provideGsonConvertFactory() }
    single{ buildOkHttpClient() }


    single{ provideNewsApiService(get()) }

    single { provideNewsRetrofit(get(),get()) }




    single{ Dispatchers.IO}
    single{ Dispatchers.Main}


}