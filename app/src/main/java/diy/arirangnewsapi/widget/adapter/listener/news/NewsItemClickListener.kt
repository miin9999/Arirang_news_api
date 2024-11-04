package diy.arirangnewsapi.widget.adapter.listener.news

import diy.arirangnewsapi.model.arirang_models.NewsDetailItem
import diy.arirangnewsapi.widget.adapter.listener.AdapterListener

interface NewsItemClickListener: AdapterListener {

    fun onItemClick(newsModel: NewsDetailItem){
    }
}