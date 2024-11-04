package diy.arirangnewsapi.screen.main.home

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import diy.arirangnewsapi.databinding.ActivityNewsDetailBinding
import diy.arirangnewsapi.model.arirang_models.NewsDetailItem

class NewsDetailActivity :AppCompatActivity() {


    lateinit var binding : ActivityNewsDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newsDetailItem: NewsDetailItem? = intent.getParcelableExtra("news_item")

        newsDetailItem?.let{
            with(binding){
                contentTextViewOfDetail.text = it.content
                titleTextviewOfDetail.text = it.title
                dateTextViewOfDetail.text=it.broadcastDate

            }
        }

        newsDetailItem?.let {
            with(binding) {
                Glide
                    .with(imageViewOfDetail.context)
                    .load(newsDetailItem.thumUrl)
                    .into(imageViewOfDetail)
            }

        }

        // textView 에 스크롤바 달아주기 - content 밑 부분이 잘리는 현상 해결
        binding.contentTextViewOfDetail.movementMethod = ScrollingMovementMethod.getInstance()


    }
}