package diy.arirangnewsapi.screen.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import diy.arirangnewsapi.data.repository.News.NewsRepository
import diy.arirangnewsapi.model.news.NewsDetailModel
import diy.arirangnewsapi.screen.DataState
import diy.arirangnewsapi.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel(
    private val newsRepository: NewsRepository
): BaseViewModel() {

    //val newsListLiveData = MutableLiveData<List<NewsDetailModel?>?>()


    val dataStateLiveData = MutableLiveData<DataState>(DataState.Uninitialized)

    override fun fetchData(): Job = viewModelScope.launch {

        dataStateLiveData.value = DataState.Loading

        val newsList = newsRepository.getNews(1,15)
        newsList?.let{
            dataStateLiveData.value = DataState.Success(newsList)
        }?:kotlin.run{
            dataStateLiveData.value = DataState.Error(
                "에러: 받아올 수 있는 뉴스가 없습니다."
            )
        }



    }


}