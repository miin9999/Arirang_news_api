package diy.arirangnewsapi.widget.adapter.listener.news

import diy.arirangnewsapi.model.word.WordModel
import diy.arirangnewsapi.widget.adapter.listener.AdapterListener

interface WordItemClickLIstener: AdapterListener{

    fun onWordItemClick(wordModel: WordModel)

    fun onWordItemLongClick(wordModel: WordModel)
}