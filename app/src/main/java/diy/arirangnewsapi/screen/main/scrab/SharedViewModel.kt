package diy.arirangnewsapi.screen.main.scrab

import android.content.Context
import android.util.Log
import androidx.appcompat.view.ActionMode
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import diy.arirangnewsapi.data.repository.News.NewsRepository
import diy.arirangnewsapi.data.repository.Translation.WordRepository
import diy.arirangnewsapi.data.repository.sharedPreference.SharedPreferencesRepository
import diy.arirangnewsapi.model.news.NewsDetailModel
import diy.arirangnewsapi.model.word.WordModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SharedViewModel(
    private val newsRepository: NewsRepository,
    private val wordRepository: WordRepository,
    private val sharedPreferencesRepository: SharedPreferencesRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {


    val isCheckBoxVisibleOfScrapedNews = MutableLiveData(false)
    val isCheckBoxVisibleOfMyWords = MutableLiveData(false)
    val isImageViewVisible = MutableLiveData(true)

    val selectedNews = MutableLiveData<MutableList<NewsDetailModel>?>(mutableListOf())
    val selectedWords = MutableLiveData<MutableList<WordModel>?>(mutableListOf())

    private val _isActionModeActive = MutableLiveData(false)
    val isActionModeActive: LiveData<Boolean> = _isActionModeActive

    private val _actionMode = MutableLiveData<ActionMode?>()
    val actionMode: LiveData<ActionMode?> = _actionMode



    fun deleteFromSharedPreference(){
        sharedPreferencesRepository.removeWordFromSharedPreferences()
    }


    fun setActionMode(mode: ActionMode?) {
        _actionMode.value = mode
    }

    fun setActionModeActive(isActive: Boolean) {
        _isActionModeActive.value = isActive
    }



    // 라디오 버튼, 바텀 버튼 토글
    fun toggleCheckBoxVisibilityOfScrap() {
        isCheckBoxVisibleOfScrapedNews.value = !(isCheckBoxVisibleOfScrapedNews.value ?: false)
        //isBottomNavVisible.value = !(isCheckBoxVisible.value ?: false)
        Log.d("scraptoggle",isCheckBoxVisibleOfScrapedNews.value.toString())
    }

    fun toggleCheckBoxVisibilityOfMyWord(){
        // 체크 박스와 이미지 뷰 토글
        isCheckBoxVisibleOfMyWords.value = !(isCheckBoxVisibleOfMyWords.value ?: false)
        isImageViewVisible.value = !(isImageViewVisible.value ?: false)
        Log.d("imageviewtoggle",isImageViewVisible.value.toString())
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

        Log.d("currentListSelectedNews", selectedNews.value.toString())
    }

    fun toggleWordSelection(item: WordModel) {
        val currentList = selectedWords.value ?: mutableListOf()
        if (currentList.contains(item)) {
            currentList.remove(item)
        } else {
            currentList.add(item)
        }
        selectedWords.value = currentList

        Log.d("currentListSelectedWord", selectedWords.value.toString())
    }



    // 삭제 로직
    suspend fun deleteSelectedNews() {
        // 선택된 아이템들의 url(primary key)를 map 으로 따오기
        val selectedUrls = selectedNews.value?.map {
            it.newsUrl
        } ?: emptyList()

        viewModelScope.launch {
            withContext(ioDispatcher) {
                newsRepository.deleteSelectedNews(selectedUrls)
                selectedNews.value?.clear()
                //deleteFromSharedPreference() 이러면 어떤 데이터를 누르든 간에 무조건 삭제함
                // 그러면 내가 선택한 데이터랑 shared랑 공통 분모가 있어야 하는데..
            }
        }

    }

    suspend fun deleteSelectedWord() {
        viewModelScope.launch {
            val selectedID = selectedWords.value?.map {
                it.id
            } ?: emptyList()

            withContext(ioDispatcher) {
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
