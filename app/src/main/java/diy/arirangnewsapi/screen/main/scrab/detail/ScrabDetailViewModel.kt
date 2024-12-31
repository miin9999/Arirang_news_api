package diy.arirangnewsapi.screen.main.scrab.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.cloud.translate.Translate
import diy.arirangnewsapi.data.entity.NewsDetailEntity
import diy.arirangnewsapi.data.entity.WordEntity
import diy.arirangnewsapi.data.repository.News.NewsRepository
import diy.arirangnewsapi.data.repository.Translation.WordRepository
import diy.arirangnewsapi.model.news.NewsDetailModel
import diy.arirangnewsapi.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ScrabDetailViewModel(
    private val newsDetailEntity: NewsDetailEntity,
    private val translateService: Translate,
    private val wordRepository: WordRepository,

    ): BaseViewModel() {


    val scrapedDetailLiveData = MutableLiveData<NewsDetailEntity>()


    override fun fetchData(): Job = viewModelScope.launch {

        scrapedDetailLiveData.value = newsDetailEntity
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


        val resultId = wordRepository.insertWord(textToWordEntity)


        if(resultId != -1L){
            Log.d("insertComplete", resultId.toString())
            Log.d("insertComplete", "succc")
        }else{
            Log.d("insertComplete", resultId.toString())
            Log.d("insertComplete", "fail")
        }
    }

}