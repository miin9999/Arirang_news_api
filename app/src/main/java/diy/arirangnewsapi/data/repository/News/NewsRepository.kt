package diy.arirangnewsapi.data.repository.News

import diy.arirangnewsapi.model.arirang_models.NewsDetailItem

interface NewsRepository {

    suspend fun getNews(pageNo:Int, numOfRows:Int) : List<NewsDetailItem?>?

}