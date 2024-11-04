package diy.arirangnewsapi.widget.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import diy.arirangnewsapi.model.arirang_models.NewsDetailItem
import diy.arirangnewsapi.databinding.NewsListBinding
import diy.arirangnewsapi.widget.adapter.listener.news.NewsItemClickListener


class NewsAdapter(
    private val listener : NewsItemClickListener
): ListAdapter<NewsDetailItem,NewsAdapter.NewsViewHolder>(differ){



    inner class NewsViewHolder(
        private val binding: NewsListBinding
    ):RecyclerView.ViewHolder(binding.root){



        fun bind(newsModel: NewsDetailItem){

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

        return NewsViewHolder(NewsListBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        Log.d("currentList onBindViewHolder",currentList.toString())
        holder.bind(currentList[position])

    }


    companion object{
        val differ = object : DiffUtil.ItemCallback<NewsDetailItem>(){
            override fun areItemsTheSame(oldNewsDetailItem: NewsDetailItem, newNewsDetailItem: NewsDetailItem): Boolean {
                TODO("Not yet implemented")
            }

            override fun areContentsTheSame(oldNewsDetailItem: NewsDetailItem, newNewsDetailItem: NewsDetailItem): Boolean {
                TODO("Not yet implemented")
            }

        }
    }


}