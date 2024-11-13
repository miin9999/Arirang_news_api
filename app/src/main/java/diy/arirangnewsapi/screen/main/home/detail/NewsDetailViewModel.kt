package diy.arirangnewsapi.screen.main.home.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import diy.arirangnewsapi.data.entity.NewsDetailEntity
import diy.arirangnewsapi.data.repository.News.DefaultNewsRepository
import diy.arirangnewsapi.data.repository.News.NewsRepository
import diy.arirangnewsapi.model.news.NewsDetailModel
import diy.arirangnewsapi.screen.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsDetailViewModel(
    private val newsDetailEntity: NewsDetailEntity,
    private val newsRepository: NewsRepository,
):BaseViewModel() {


    // 디테일 액티티비니까 어차피 데이터는 한 가지만 들어옴
    // 현재 내가 보고 있는 뉴스의 데이터만 있는 상태
    val scrapedNewsLiveData = MutableLiveData<NewsDetailEntity>()

    override fun fetchData(): Job = viewModelScope.launch {
        scrapedNewsLiveData.value = newsDetailEntity
    }




    fun insertThisNews() = viewModelScope.launch {

        // room에 insert해주기
        newsRepository.insertNews(newsDetailEntity)
        Log.d("insertedNews.title", newsDetailEntity.title.toString())


    }
}