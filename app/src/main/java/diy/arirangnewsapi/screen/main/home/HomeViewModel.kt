package diy.arirangnewsapi.screen.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import diy.arirangnewsapi.data.repository.News.NewsRepository
import diy.arirangnewsapi.model.news.NewsDetailModel
import diy.arirangnewsapi.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel(
    private val newsRepository: NewsRepository
): BaseViewModel() {

    val newsListLiveData = MutableLiveData<List<NewsDetailModel?>?>()


    override fun fetchData(): Job = viewModelScope.launch {

        val newsList = newsRepository.getNews(1,15)

        newsListLiveData.value = newsList

    }


}