package diy.arirangnewsapi.screen.main.scrab

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import diy.arirangnewsapi.data.repository.News.NewsRepository
import diy.arirangnewsapi.data.repository.Translation.WordRepository
import diy.arirangnewsapi.model.news.NewsDetailModel
import diy.arirangnewsapi.model.word.WordModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SharedViewModel(
    private val newsRepository: NewsRepository,
    private val wordRepository: WordRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {


    val isCheckBoxVisibleOfScrapedNews = MutableLiveData(false)
    val isCheckBoxVisibleOfMyWords = MutableLiveData(false)
    val isImageViewVisible = MutableLiveData(true)

    val selectedNews = MutableLiveData<MutableList<NewsDetailModel>?>(mutableListOf())
    val selectedWords = MutableLiveData<MutableList<WordModel>?>(mutableListOf())





    // 라디오 버튼, 바텀 버튼 토글
    fun toggleCheckBoxVisibilityOfScrap() {
        isCheckBoxVisibleOfScrapedNews.value = !(isCheckBoxVisibleOfScrapedNews.value ?: false)
        //isBottomNavVisible.value = !(isCheckBoxVisible.value ?: false)
    }

    fun toggleCheckBoxVisibilityOfMyWord(){
        // 체크 박스와 이미지 뷰 토글
        isCheckBoxVisibleOfMyWords.value = !(isCheckBoxVisibleOfMyWords.value ?: false)
        isImageViewVisible.value = !(isImageViewVisible.value ?: false)
    }



    // 아이템 선택 추가/제거
    fun toggleNewsSelection(item: NewsDetailModel) {
        val currentList = selectedNews.value ?: mutableListOf()
        if (currentList.contains(item)) {
            currentList.remove(item)
        } else {
            currentList.add(item)
        }
        selectedNews.value = currentList

        Log.d("currentListSelected", selectedNews.value.toString())
    }

    fun toggleWordSelection(item: WordModel) {
        val currentList = selectedWords.value ?: mutableListOf()
        if (currentList.contains(item)) {
            currentList.remove(item)
        } else {
            currentList.add(item)
        }
        selectedWords.value = currentList

        Log.d("currentListSelected", selectedWords.value.toString())
    }



    // 삭제 로직
    suspend fun deleteSelectedNews() {
        // 선택된 아이템들의 url(primary key)를 따옴
        viewModelScope.launch {
            withContext(ioDispatcher) {
                val selectedUrls = selectedNews.value?.map {
                    it.newsUrl
                } ?: emptyList()

                newsRepository.deleteSelectedNews(selectedUrls)
                selectedNews.value?.clear()
            }
        }

    }

    suspend fun deleteSelectedWord() {
        viewModelScope.launch {
            withContext(ioDispatcher) {
                val selectedID = selectedWords.value?.map {
                    it.id
                } ?: emptyList()

                wordRepository.deleteSelectedWords(selectedID)
                selectedWords.value?.clear()

            }
        }

    }

    // 취소 로직
    fun cancelSelection() {
        selectedNews.value = mutableListOf()
        isCheckBoxVisibleOfScrapedNews.value = false
    }

}
