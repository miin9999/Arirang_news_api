package diy.arirangnewsapi.widget.adapter.listener.news

import diy.arirangnewsapi.model.news.NewsDetailModel
import diy.arirangnewsapi.widget.adapter.listener.AdapterListener

interface NewsItemClickListener: AdapterListener {

    fun onItemClick(newsDetailModel: NewsDetailModel){
    }



}