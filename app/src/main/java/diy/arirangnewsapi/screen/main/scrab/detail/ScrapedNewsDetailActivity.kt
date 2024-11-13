package diy.arirangnewsapi.screen.main.scrab.detail

import android.content.Context
import android.content.Intent
import android.text.method.ScrollingMovementMethod
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import diy.arirangnewsapi.R
import diy.arirangnewsapi.data.entity.NewsDetailEntity
import diy.arirangnewsapi.databinding.ActivityScrapedNewsDetailBinding
import diy.arirangnewsapi.screen.base.BaseActivity
import diy.arirangnewsapi.screen.main.scrab.ScrabViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ScrapedNewsDetailActivity : BaseActivity<ScrabDetailViewModel, ActivityScrapedNewsDetailBinding>() {


    companion object {
        fun newIntent(context: Context, newsDetailEntity: NewsDetailEntity) =
            Intent(context, ScrapedNewsDetailActivity::class.java).apply {
                putExtra("news_item", newsDetailEntity)
            }

    }

    override val viewModel by viewModel<ScrabDetailViewModel> {
        parametersOf(
            intent.getParcelableExtra<NewsDetailEntity>("news_item")
        )
    }


    override fun getViewBinding(): ActivityScrapedNewsDetailBinding =
        ActivityScrapedNewsDetailBinding.inflate(layoutInflater)


    override fun initViews() {

    }

    override fun observeData() = viewModel.scrapedDetailLiveData.observe(this@ScrapedNewsDetailActivity) {
        with(binding) {
            contentTextViewOfDetail.text = it.content
            titleTextviewOfDetail.text = it.title
            dateTextViewOfDetail.text = it.broadcastDate
            // Log.d("news Url",it.newsUrl.toString())

        }

        with(binding) {
            Glide
                .with(imageViewOfDetail.context)
                .load(it.thumUrl)
                .into(imageViewOfDetail)
            // textView 에 스크롤바 달아주기 - content 밑 부분이 잘리는 현상 해결
            contentTextViewOfDetail.movementMethod = ScrollingMovementMethod.getInstance()

        }


        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.title = ""


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home->{
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



}