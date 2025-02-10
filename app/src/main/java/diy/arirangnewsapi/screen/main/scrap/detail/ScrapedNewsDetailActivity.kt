package diy.arirangnewsapi.screen.main.scrap.detail

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import diy.arirangnewsapi.data.entity.NewsDetailEntity
import diy.arirangnewsapi.databinding.ActivityScrapedNewsDetailBinding
import diy.arirangnewsapi.screen.base.BaseActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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


    override fun initViews() =with(binding) {

        setupTextSelectionActionMode(contentTextViewOfDetail)
        setupTextSelectionActionMode(titleTextviewOfDetail)

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
                    Toast.makeText(this@ScrapedNewsDetailActivity, "단어가 저장되었습니다!", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e("Error", "작업 실패: ${e.message}")
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ScrapedNewsDetailActivity, "저장 중 오류 발생!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }




    private suspend fun addToVocabulary(textThatIWantToTranslate: String) {
        viewModel.addButtonToVoca(textThatIWantToTranslate)
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
            //contentTextViewOfDetail.movementMethod = ScrollingMovementMethod.getInstance()

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