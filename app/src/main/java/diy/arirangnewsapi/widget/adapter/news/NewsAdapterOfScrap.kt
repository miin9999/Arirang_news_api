package diy.arirangnewsapi.widget.adapter.news

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import diy.arirangnewsapi.model.news.NewsDetailModel
import diy.arirangnewsapi.databinding.NewsListScrapBinding
import diy.arirangnewsapi.screen.main.scrab.ScrabViewModel
import diy.arirangnewsapi.widget.adapter.listener.news.NewsItemClickListener


class NewsAdapterOfScrap(
    private val listener : NewsItemClickListener,
    private val viewModel: ScrabViewModel
): ListAdapter<NewsDetailModel, NewsAdapterOfScrap.NewsViewHolder>(differ){

    inner class NewsViewHolder(
        private val binding: NewsListScrapBinding
    ):RecyclerView.ViewHolder(binding.root){


        fun bind(newsModel: NewsDetailModel){

            binding.titleTextView.text = newsModel.title
            binding.contentTextView.text = newsModel.content
            binding.root.setOnClickListener{
                listener.onItemClick(newsModel)

            }

            val isRadioVisible = viewModel.isRadioButtonsVisible.value ?: false
            binding.radioButton.visibility = if (isRadioVisible) View.VISIBLE else View.GONE

            // 아이템을 길게 눌렀을 때 라디오 버튼을 보이도록 처리
            binding.root.setOnLongClickListener {
                // 모든 아이템의 라디오 버튼을 보이게 하기 위해 ViewModel의 상태 변경
                viewModel.toggleRadioButtonsVisibility()
                notifyDataSetChanged() // 전체 아이템을 다시 그리게 함
                true
            }

            Glide
                .with(binding.coverImageView.context)
                .load(newsModel.thumUrl)
                .into(binding.coverImageView)

        }
    }


    override fun getItemCount(): Int = currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        return NewsViewHolder(NewsListScrapBinding.inflate(LayoutInflater.from(parent.context),parent,false))


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