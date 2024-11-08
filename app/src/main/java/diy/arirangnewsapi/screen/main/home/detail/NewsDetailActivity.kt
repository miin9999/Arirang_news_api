package diy.arirangnewsapi.screen.main.home.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import diy.arirangnewsapi.R
import diy.arirangnewsapi.data.entity.NewsDetailEntity
import diy.arirangnewsapi.databinding.ActivityNewsDetailBinding
import diy.arirangnewsapi.model.news.NewsDetailModel
import diy.arirangnewsapi.screen.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class NewsDetailActivity : BaseActivity<NewsDetailViewModel, ActivityNewsDetailBinding>() {


    companion object {
        fun newIntent(context: Context, newsDetailEntity: NewsDetailEntity) =
            Intent(context, NewsDetailActivity::class.java).apply {
                putExtra("news_item", newsDetailEntity)
            }

    }

    override val viewModel by viewModel<NewsDetailViewModel> {
        parametersOf(
            intent.getParcelableExtra<NewsDetailEntity>("news_item")
        )
    }


    override fun getViewBinding(): ActivityNewsDetailBinding =
        ActivityNewsDetailBinding.inflate(layoutInflater)


    override fun initViews() = Unit

    override fun observeData() = viewModel.scrapedNewsLiveData.observe(this@NewsDetailActivity) {
        it?.let {
            with(binding) {
                contentTextViewOfDetail.text = it.content
                titleTextviewOfDetail.text = it.title
                dateTextViewOfDetail.text = it.broadcastDate
                // Log.d("news Url",it.newsUrl.toString())

            }
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


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {

            android.R.id.home -> {
                finish()
                true
            }

            R.id.ic_save -> {
                Toast.makeText(this, "스크랩되었습니다", Toast.LENGTH_SHORT).show()
                viewModel.insertThisNews()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


}