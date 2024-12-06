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
import diy.arirangnewsapi.screen.main.scrab.SharedViewModel
import diy.arirangnewsapi.widget.adapter.listener.news.NewsItemClickListener


class NewsAdapterOfScrap(
    private val listener : NewsItemClickListener,
    private val viewModel: SharedViewModel
): ListAdapter<NewsDetailModel, NewsAdapterOfScrap.NewsViewHolder>(differ){

    inner class NewsViewHolder(
        private val binding: NewsListScrapBinding
    ):RecyclerView.ViewHolder(binding.root){


        fun bind(newsModel: NewsDetailModel){

            binding.titleTextView.text = newsModel.title
            binding.contentTextView.text = newsModel.content

            binding.checkBox.setOnCheckedChangeListener(null)
            binding.checkBox.isChecked = false

            binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    // 체크박스를 체크한 경우
                    viewModel.toggleNewsSelection(newsModel)
                } else {
                    // 체크박스를 체크 해제한 경우
                    viewModel.toggleNewsSelection(newsModel)
                }


                viewModel.actionMode.value?.invalidate()
            }


            binding.root.setOnClickListener {
                val isCheckBoxVisible = viewModel.isCheckBoxVisibleOfScrapedNews.value ?: false
                if (isCheckBoxVisible) {
                    // 체크박스가 보이면 체크박스를 클릭하는 동작
                    binding.checkBox.performClick()
                } else {
                    // 원래의 아이템 클릭 동작
                    listener.onItemClick(newsModel)
                }
            }
            binding.root.setOnLongClickListener {

                // 액션 모드가 활성화된 경우 롱클릭을 무시
                viewModel.isActionModeActive.value?.let { isActionModeActive ->
                    if (isActionModeActive) {
                        return@setOnLongClickListener false
                    }
                }

                // ViewModel의 상태를 변경하여 체크박스 표시
                viewModel.toggleCheckBoxVisibilityOfScrap()
                listener.onLongItemClick(newsModel)
                true
            }

            val isCheckBoxVisible = viewModel.isCheckBoxVisibleOfScrapedNews.value ?: false
            binding.checkBox.visibility = if (isCheckBoxVisible) View.VISIBLE else View.GONE


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