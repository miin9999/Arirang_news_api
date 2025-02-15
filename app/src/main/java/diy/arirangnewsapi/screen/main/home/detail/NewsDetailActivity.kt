package diy.arirangnewsapi.screen.main.home.detail

import android.annotation.SuppressLint
import android.view.ActionMode
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import diy.arirangnewsapi.R
import diy.arirangnewsapi.data.entity.NewsDetailEntity
import diy.arirangnewsapi.databinding.ActivityNewsDetailBinding
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


    @SuppressLint("ClickableViewAccessibility")
    override fun initViews() = with(binding) {

        setupTextSelectionActionMode(contentTextViewOfDetail)
        setupTextSelectionActionMode(titleTextviewOfDetail)

        //Log.d("ActionMode", "Action mode started222??")

    }

    private fun setupTextSelectionActionMode(textView: TextView) {
        textView.customSelectionActionModeCallback = object : ActionMode.Callback {
            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                Log.d("ActionMode", "Action mode started for ${textView.id}")

                menu?.apply {
                    clear()
                    add("추가").setOnMenuItemClickListener {
                        val selectedText = textView.text.substring(
                            textView.selectionStart,
                            textView.selectionEnd
                        )
                        saveSelectedText(selectedText)
                        mode?.finish() // 액션 모드 종료
                        true
                    }
                }
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean = false
            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean = false
            override fun onDestroyActionMode(mode: ActionMode?) {}
        }
    }

    private fun saveSelectedText(selectedText: String) {
        lifecycleScope.launch {
            try {
                withContext(Dispatchers.IO) { addToVocabulary(selectedText) }
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@NewsDetailActivity, "단어가 저장되었습니다!", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e("Error", "작업 실패: ${e.message}")
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@NewsDetailActivity, "저장 중 오류 발생!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    suspend fun addToVocabulary(textThatIWantToTranslate: String) {
        viewModel.addButtonToVoca(textThatIWantToTranslate)
    }





    override fun observeData() =
        viewModel.currentNewsImLookingInDetailLiveData.observe(this@NewsDetailActivity) {
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

//                Toast.makeText(this, "스크랩되었습니다", Toast.LENGTH_SHORT).show()
//                viewModel.insertThisNews()


                viewModel.currentNewsImLookingInDetailLiveData.observe(this@NewsDetailActivity) {

                    lifecycleScope.launch {
                        var temp = viewModel.isNewsScraped(it.newsUrl)
                        Log.d("fecbbcb", it.newsUrl)
                        Log.d("fecbbcb", temp.toString())

                        if (temp > 0) {
                            Toast.makeText(
                                this@NewsDetailActivity,
                                "이미 스크랩된 뉴스입니다",
                                Toast.LENGTH_SHORT
                            ).show()
                            temp = 0
                        } else {
                            Toast.makeText(this@NewsDetailActivity, "스크랩되었습니다", Toast.LENGTH_SHORT)
                                .show()
                            viewModel.insertThisNews()
                        }

                    }
                }

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}