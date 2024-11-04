package diy.arirangnewsapi.data.repository.News

import diy.arirangnewsapi.data.network.NewsService
import diy.arirangnewsapi.data.url.Url
import diy.arirangnewsapi.model.arirang_models.NewsDetailItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class DefaultNewsRepository(
    private val newsApiService:NewsService,
    private val ioDispatcher: CoroutineDispatcher
): NewsRepository {

    // 다른 화면에서 쓸 suspend 함수
    override suspend fun getNews(
        pageNo:Int,numOfRows:Int
    ) : List<NewsDetailItem?>?= withContext(ioDispatcher){
        newsApiService.getNews(pageNo = pageNo, numOfRows = numOfRows)
            .body()
            ?.newsDetailItems
    }




}