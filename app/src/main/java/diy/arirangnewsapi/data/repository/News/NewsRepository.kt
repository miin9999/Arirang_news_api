package diy.arirangnewsapi.data.repository.News

import androidx.lifecycle.LiveData
import diy.arirangnewsapi.data.entity.NewsDetailEntity
import diy.arirangnewsapi.model.news.NewsDetailModel

interface NewsRepository {

    // api에서 받아 오는 함수
    suspend fun getNews(pageNo:Int, numOfRows:Int) : List<NewsDetailModel?>?


    // DB 작업 ↓
    // 하나의 스크랩된 뉴스 가져오기
    suspend fun getOneScrapedNews(newsId:Long):NewsDetailEntity?

    // 이미 존재하는 스크랩된 모든 뉴스 가져오기
    fun getAllScrapedNews(): LiveData<List<NewsDetailEntity?>>

    // db에 뉴스 스크랩(저장)
    suspend fun insertNews(newsDetailEntity: NewsDetailEntity)

    // 스크랩된 뉴스 전체 삭제
    suspend fun clearAllScrapedNews()

    suspend fun isNewsScraped(newsUrl: String):Int


}