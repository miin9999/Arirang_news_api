package diy.arirangnewsapi.widget.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import diy.arirangnewsapi.databinding.NewsListHomeBinding
import diy.arirangnewsapi.model.news.NewsDetailModel
import diy.arirangnewsapi.widget.adapter.listener.news.NewsItemClickListener


class NewsAdapterOfHome(
    private val listener : NewsItemClickListener
): ListAdapter<NewsDetailModel,NewsAdapterOfHome.NewsViewHolder>(differ){



    inner class NewsViewHolder(
        private val binding: NewsListHomeBinding
    ):RecyclerView.ViewHolder(binding.root){



        fun bind(newsModel: NewsDetailModel){

            binding.titleTextView.text = newsModel.title
            binding.contentTextView.text = newsModel.content
            binding.root.setOnClickListener{
                listener.onItemClick(newsModel)

            }

            Glide
                .with(binding.coverImageView.context)
                .load(newsModel.thumUrl)
                .into(binding.coverImageView)

        }
    }


    override fun getItemCount(): Int = currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        return NewsViewHolder(NewsListHomeBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        Log.d("currentList onBindViewHolder",currentList.toString())
        holder.bind(currentList[position])

    }


    companion object{
        val differ = object : DiffUtil.ItemCallback<NewsDetailModel>(){
            override fun areItemsTheSame(oldNewsDetailModel: NewsDetailModel, newNewsDetailModel: NewsDetailModel): Boolean {
                return oldNewsDetailModel.newsUrl == newNewsDetailModel.newsUrl
            }

            override fun areContentsTheSame(oldNewsDetailModel: NewsDetailModel, newNewsDetailModel: NewsDetailModel): Boolean {
                return oldNewsDetailModel == newNewsDetailModel
            }

        }
    }


}