package diy.arirangnewsapi.di


import diy.arirangnewsapi.data.entity.NewsDetailEntity
import diy.arirangnewsapi.data.repository.News.DefaultNewsRepository
import diy.arirangnewsapi.data.repository.News.NewsRepository
import diy.arirangnewsapi.data.repository.Translation.DefaultTransRepository
import diy.arirangnewsapi.data.repository.Translation.TransRepository
import diy.arirangnewsapi.model.word.WordModel
import diy.arirangnewsapi.screen.main.home.HomeViewModel
import diy.arirangnewsapi.screen.main.home.detail.NewsDetailViewModel
import diy.arirangnewsapi.screen.main.myword.MyWordViewModel
import diy.arirangnewsapi.screen.main.myword.detail.WordDetailViewModel
import diy.arirangnewsapi.screen.main.profile.ProfileViewModel
import diy.arirangnewsapi.screen.main.scrab.ScrabViewModel
import diy.arirangnewsapi.screen.main.scrab.detail.ScrabDetailViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication


import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{

    viewModel{ HomeViewModel(get())}
    viewModel{ MyWordViewModel(get())}
    viewModel{ (wordModel: WordModel) -> WordDetailViewModel(wordModel)}
    viewModel{ ProfileViewModel()}
    viewModel{ ScrabViewModel(get())}
    viewModel{ (newsDetailEntity:NewsDetailEntity)-> ScrabDetailViewModel(newsDetailEntity) }
    viewModel{ (newsDetailEntity:NewsDetailEntity) -> NewsDetailViewModel(newsDetailEntity,get(),get(),get(),get()) }


    single<NewsRepository>{ DefaultNewsRepository(get(),get(),get()) }
    single<TransRepository>{DefaultTransRepository(get(),get())}




    single{ provideGsonConvertFactory() }
    single{ buildOkHttpClient() }
    single { provideNewsRetrofit(get(),get()) }
    single {provideTranslateService(androidApplication())}



    single{ provideNewsApiService(get()) }



    single{ provideDB(androidApplication()) }
    single { provideNewsDao(get()) }
    single { provideWordDao(get()) }



    single{ Dispatchers.IO }
    single{ Dispatchers.Main }



}