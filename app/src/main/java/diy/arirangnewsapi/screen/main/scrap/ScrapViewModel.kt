package diy.arirangnewsapi.screen.main.scrap

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import diy.arirangnewsapi.data.entity.NewsDetailEntity
import diy.arirangnewsapi.data.repository.News.NewsRepository
import diy.arirangnewsapi.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ScrapViewModel(
    newsRepository: NewsRepository
): BaseViewModel() {


    val scrapedFragmentLiveData: LiveData<List<NewsDetailEntity?>> = newsRepository.getAllScrapedNews()
    var scrapedNewsCount : LiveData<Int> = newsRepository.getScrapedNewsCount()

    override fun fetchData(): Job = viewModelScope.launch {


    }

}