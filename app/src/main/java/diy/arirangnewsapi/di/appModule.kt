package diy.arirangnewsapi.di


import diy.arirangnewsapi.data.entity.NewsDetailEntity
import diy.arirangnewsapi.data.repository.News.DefaultNewsRepository
import diy.arirangnewsapi.data.repository.News.NewsRepository
import diy.arirangnewsapi.data.repository.Translation.DefaultWordRepository
import diy.arirangnewsapi.data.repository.Translation.WordRepository
import diy.arirangnewsapi.data.repository.sharedPreference.SharedPreferencesRepository
import diy.arirangnewsapi.model.word.WordModel
import diy.arirangnewsapi.screen.main.home.HomeViewModel
import diy.arirangnewsapi.screen.main.home.detail.NewsDetailViewModel
import diy.arirangnewsapi.screen.main.myword.MyWordViewModel
import diy.arirangnewsapi.screen.main.myword.detail.WordDetailViewModel
import diy.arirangnewsapi.screen.main.profile.TodayWordViewModel
import diy.arirangnewsapi.screen.main.scrap.ScrapViewModel
import diy.arirangnewsapi.screen.main.scrap.SharedViewModel
import diy.arirangnewsapi.screen.main.scrap.detail.ScrabDetailViewModel
import diy.arirangnewsapi.screen.main.setting.SettingViewModel
import diy.arirangnewsapi.screen.main.setting.api_reference.ApiReferenceActivity
import diy.arirangnewsapi.screen.main.setting.api_reference.ApiReferenceViewModel
import diy.arirangnewsapi.screen.main.setting.license.LicenseViewModel

import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication


import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { HomeViewModel(get()) }
    viewModel { MyWordViewModel(get()) }
    viewModel { (wordModel: WordModel) -> WordDetailViewModel(wordModel) }
    viewModel { TodayWordViewModel(get(), get()) }
    viewModel { ScrapViewModel(get()) }
    viewModel { (newsDetailEntity: NewsDetailEntity) ->
        ScrabDetailViewModel(
            newsDetailEntity,
            get(),
            get()
        )
    }
    viewModel { (newsDetailEntity: NewsDetailEntity) ->
        NewsDetailViewModel(
            newsDetailEntity,
            get(),
            get(),
            get(),
            get()
        )
    }
    viewModel { SharedViewModel(get(), get(), get(), get()) }
    viewModel { SettingViewModel() }
    viewModel {LicenseViewModel()}
    viewModel{ApiReferenceViewModel()}


    single<NewsRepository> { DefaultNewsRepository(get(), get(), get()) }
    single<WordRepository> { DefaultWordRepository(get(), get()) }

    single { SharedPreferencesRepository(get()) }


    single { provideGsonConvertFactory() }
    single { buildOkHttpClient() }
    single { provideNewsRetrofit(get(), get()) }
    single { provideTranslateService(androidApplication()) }


    //single<CustomWorkerFactory>{CustomWorkerFactory()}

    single { provideNewsApiService(get()) }



    single { provideDB(androidApplication()) }
    single { provideNewsDao(get()) }
    single { provideWordDao(get()) }



    single { Dispatchers.IO }
    single { Dispatchers.Main }


}