package diy.arirangnewsapi.data.repository.News

import diy.arirangnewsapi.data.db.dao.NewsDao
import diy.arirangnewsapi.data.entity.NewsDetailEntity
import diy.arirangnewsapi.data.network.NewsService
import diy.arirangnewsapi.model.news.NewsDetailModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultNewsRepository(
    private val newsApiService:NewsService,
    private val ioDispatcher: CoroutineDispatcher,
    private val newsDao: NewsDao
): NewsRepository {

    // 다른 화면에서 쓸 suspend 함수
    override suspend fun getNews(
        pageNo:Int,numOfRows:Int
    ) : List<NewsDetailModel?>?= withContext(ioDispatcher){
        newsApiService.getNews(pageNo = pageNo, numOfRows = numOfRows)
            .body()
            ?.newsDetailModels
    }

    override suspend fun getOneScrapedNews(newsId: Long): NewsDetailEntity? = withContext(ioDispatcher) {
        newsDao.getOneNews(newsId)
    }

    override suspend fun getAllScrapedNews(): List<NewsDetailEntity?> = withContext(ioDispatcher){
        newsDao.getAllNews()
    }

    override suspend fun insertNews(newsDetailEntity: NewsDetailEntity) = withContext(ioDispatcher){
        newsDao.insertNews(newsDetailEntity)
    }

    override suspend fun clearAllScrapedNews() = withContext(ioDispatcher) {
        newsDao.deleteAllNews()
    }
}