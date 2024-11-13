package diy.arirangnewsapi.screen.main.scrab.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import diy.arirangnewsapi.data.entity.NewsDetailEntity
import diy.arirangnewsapi.data.repository.News.NewsRepository
import diy.arirangnewsapi.model.news.NewsDetailModel
import diy.arirangnewsapi.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ScrabDetailViewModel(
    private val newsDetailEntity: NewsDetailEntity,
): BaseViewModel() {


    val scrapedDetailLiveData = MutableLiveData<NewsDetailEntity>()


    override fun fetchData(): Job = viewModelScope.launch {

        scrapedDetailLiveData.value = newsDetailEntity
    }

}