package diy.arirangnewsapi.data.network

import diy.arirangnewsapi.BuildConfig
import diy.arirangnewsapi.model.news.NewsResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {


    @GET("news?" +
            "&serviceKey=${BuildConfig.arirang_api_key}")
    suspend fun getNews(
        @Query("pageNo") pageNo:Int,
        @Query("numOfRows") numOfRows:Int,
    ):Response<NewsResponseModel>

}