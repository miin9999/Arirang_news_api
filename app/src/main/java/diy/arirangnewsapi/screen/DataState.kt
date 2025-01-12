package diy.arirangnewsapi.screen

import diy.arirangnewsapi.model.news.NewsDetailModel

sealed class DataState {


    object Uninitialized: DataState()
    object Loading : DataState()
    data class Success(val data: List<NewsDetailModel?>?) : DataState()
    data class Error(val message: String) : DataState()
}