package diy.arirangnewsapi.screen.main.myword.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import diy.arirangnewsapi.data.entity.NewsDetailEntity
import diy.arirangnewsapi.model.word.WordModel
import diy.arirangnewsapi.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class WordDetailViewModel(
    private val wordModel: WordModel
):BaseViewModel() {


    val currentWordImLookingLiveData = MutableLiveData<WordModel>()

    override fun fetchData(): Job = viewModelScope.launch {

        currentWordImLookingLiveData.value = wordModel
    }


}