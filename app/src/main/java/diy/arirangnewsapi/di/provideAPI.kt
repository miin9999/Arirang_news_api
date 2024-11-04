package diy.arirangnewsapi.di

import diy.arirangnewsapi.BuildConfig
import diy.arirangnewsapi.data.network.NewsService
import diy.arirangnewsapi.data.url.Url
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun provideNewsApiService(retrofit: Retrofit): NewsService {
    return retrofit.create(NewsService::class.java)
}

fun provideNewsRetrofit(
    okHttpClient: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory

): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Url.ARIRANG_BASE_URL)
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build()

}

fun provideGsonConvertFactory():GsonConverterFactory{
    return GsonConverterFactory.create()
}


fun buildOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    if(BuildConfig.DEBUG){
        interceptor.level = HttpLoggingInterceptor.Level.BODY
    }else{
        interceptor.level = HttpLoggingInterceptor.Level.NONE
    }
    return OkHttpClient.Builder()
        .connectTimeout(4, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()
}