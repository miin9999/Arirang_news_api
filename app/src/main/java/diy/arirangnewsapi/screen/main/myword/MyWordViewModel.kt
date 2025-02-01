package diy.arirangnewsapi.screen.main.myword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import diy.arirangnewsapi.data.entity.WordEntity
import diy.arirangnewsapi.data.repository.Translation.WordRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

import diy.arirangnewsapi.screen.base.BaseViewModel


class MyWordViewModel(
    private val wordRepository: WordRepository
): BaseViewModel() {



    val wordsFromRoom: LiveData<List<WordEntity?>> = wordRepository.getAllWord()
    var wordCount : LiveData<Int> = wordRepository.getWordCount()

    override fun fetchData(): Job = viewModelScope.launch {
    }

}


