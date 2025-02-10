package diy.arirangnewsapi.screen.main.home

import android.util.Log
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
) : BaseViewModel() {

    private var currentPage = 1
    private val pageSize = 10
    private val newsList = mutableListOf<NewsDetailModel>() // 전체 뉴스 리스트 저장

    val dataStateLiveData = MutableLiveData<DataState>(DataState.Uninitialized)

    override fun fetchData(): Job = viewModelScope.launch {
        if (dataStateLiveData.value is DataState.Loading) return@launch // 중복 요청 방지

        if (currentPage > 5) {
            return@launch // currentPage가 5를 초과하면 더 이상 요청하지 않음
        }

        dataStateLiveData.value = DataState.Loading

        val newNews = newsRepository.getNews(currentPage, pageSize)
            ?.filterNotNull() // null 값 제거

        if (!newNews.isNullOrEmpty()) {
            newsList.addAll(newNews) // 기존 리스트에 추가
            Log.d("vvfvbfbdfb", newNews.last().title.toString())
            currentPage++ // 페이지 증가
            dataStateLiveData.value = DataState.Success(newsList.toList()) // 새로운 리스트 전달
        } else {
            dataStateLiveData.value = DataState.Error("에러: 받아올 수 있는 뉴스가 없습니다.")
        }
    }

}
