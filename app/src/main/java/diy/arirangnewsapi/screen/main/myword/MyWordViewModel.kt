package diy.arirangnewsapi.screen.main.myword

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import diy.arirangnewsapi.data.entity.NewsDetailEntity
import diy.arirangnewsapi.data.entity.WordEntity
import diy.arirangnewsapi.data.repository.Translation.TransRepository
import diy.arirangnewsapi.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MyWordViewModel(
    private val transRepository: TransRepository
): BaseViewModel() {


    //todo 어쨌든 여기선 첫번째로, room에 있는 데이터를 가져와야 함
    // 다른 기능은 나중에 생각


    val wordsFromRoom: LiveData<List<WordEntity?>> = transRepository.getAllWord()



    override fun fetchData():Job = viewModelScope.launch {
    }






}