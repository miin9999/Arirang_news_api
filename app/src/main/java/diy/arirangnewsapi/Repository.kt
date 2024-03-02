package diy.arirangnewsapi

import diy.arirangnewsapi.arirang_models.Item
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

object Repository {

    //todo suspend 함수

    suspend fun getNewsFromApiService(pageNo:Int,numOfRows:Int) : List<Item?>?=
            retrofitOfArirang.getNews(pageNo = pageNo, numOfRows = numOfRows)
            .body()
            ?.items

    // todo retrofit 빌더


    private val retrofitOfArirang : ArirangService by lazy{
        Retrofit.Builder()
            .baseUrl(Url.ARIRANG_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

}