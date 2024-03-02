package diy.arirangnewsapi

import diy.arirangnewsapi.arirang_models.ArirangResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArirangService {


    @GET("news?" +
            "&serviceKey=${BuildConfig.arirang_api_key}")
    suspend fun getNews(
        @Query("pageNo") pageNo:Int,
        @Query("numOfRows") numOfRows:Int,
    ):Response<ArirangResponseModel>

}