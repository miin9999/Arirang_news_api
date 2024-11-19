package diy.arirangnewsapi.screen.main.home.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.cloud.translate.Translate

import diy.arirangnewsapi.data.entity.NewsDetailEntity
import diy.arirangnewsapi.data.entity.WordEntity
import diy.arirangnewsapi.data.repository.News.NewsRepository
import diy.arirangnewsapi.data.repository.Translation.TransRepository
import diy.arirangnewsapi.screen.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class NewsDetailViewModel(
    private val newsDetailEntity: NewsDetailEntity,
    private val newsRepository: NewsRepository,
    private val transRepository: TransRepository,
    private val translateService: Translate,
    private val ioDispatcher: CoroutineDispatcher
) : BaseViewModel() {



    // 디테일 액티티비니까 어차피 데이터는 한 가지만 들어옴
    // 현재 내가 보고 있는 뉴스의 데이터만 있는 상태


    val currentNewsImLookingInDetailLiveData = MutableLiveData<NewsDetailEntity>()


    override fun fetchData()= viewModelScope.launch {
        currentNewsImLookingInDetailLiveData.value = newsDetailEntity
    }


    suspend fun addButtonToVoca(originalWord: String) {

        val translation = translateService.translate(
            originalWord,
            Translate.TranslateOption.targetLanguage("ko")
        )
        // [원래 단어,번역된 단어]를 wordEntity 로 바꾸기
        val textToWordEntity = WordEntity(
            originalWord = originalWord,
            translatedWord = translation.translatedText
        )

        Log.d("insertComplete", textToWordEntity.toString())


        val resultid = transRepository.insertWord(textToWordEntity)
        if(resultid != -1L){
            Log.d("insertComplete", resultid.toString())
            Log.d("insertComplete", "succc")
        }else{
            Log.d("insertComplete", resultid.toString())
            Log.d("insertComplete", "fail")
        }


    }


    fun insertThisNews() = viewModelScope.launch {

        // room에 insert해주기
        newsRepository.insertNews(newsDetailEntity)
        Log.d("insertedNews.title", newsDetailEntity.title.toString())


    }

    suspend fun isNewsScraped(newsUrl: String): Int {
        return newsRepository.isNewsScraped(newsUrl)

    }
}