package diy.arirangnewsapi.screen.main.scrab

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import diy.arirangnewsapi.data.repository.News.NewsRepository
import diy.arirangnewsapi.model.news.NewsDetailModel

class SharedViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {


    val isCheckBoxVisible = MutableLiveData(false) // 라디오 버튼 보임 여부
    val selectedItems = MutableLiveData<MutableList<NewsDetailModel>?>(mutableListOf())
    //val isBottomNavVisible = MutableLiveData(true) // 바텀 내비게이션 보임 여부


    // 라디오 버튼, 바텀 버튼 토글
    fun toggleCheckBoxVisibility() {
        isCheckBoxVisible.value = !(isCheckBoxVisible.value ?: false)
        //isBottomNavVisible.value = !(isCheckBoxVisible.value ?: false)
    }

    // 아이템 선택 추가/제거
    fun toggleItemSelection(item: NewsDetailModel) {
        val currentList = selectedItems.value ?: mutableListOf()
        if (currentList.contains(item)) {
            currentList.remove(item)
        } else {
            currentList.add(item)
        }
        selectedItems.value = currentList

        Log.d("currentListSelected", selectedItems.value.toString())
    }

    // 삭제 로직
    suspend fun deleteSelectedItems() {
        // 선택된 아이템들의 url(primary key)를 따옴
        val selectedUrls = selectedItems.value?.map{
            it.newsUrl
        } ?: emptyList()

        newsRepository.deleteSelectedNews(selectedUrls)
        selectedItems.value?.clear()

    }

    // 취소 로직
    fun cancelSelection() {
        selectedItems.value = mutableListOf()
        isCheckBoxVisible.value = false
    }
}
