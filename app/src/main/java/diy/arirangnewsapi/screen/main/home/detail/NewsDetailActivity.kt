package diy.arirangnewsapi.screen.main.home.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.text.method.ScrollingMovementMethod
import android.util.AttributeSet
import android.util.Log
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.widget.PopupMenu
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.translate.Translate
import com.google.cloud.translate.TranslateOptions
import diy.arirangnewsapi.R
import diy.arirangnewsapi.data.entity.NewsDetailEntity
import diy.arirangnewsapi.databinding.ActivityNewsDetailBinding
import diy.arirangnewsapi.screen.base.BaseActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.w3c.dom.Text
import java.io.InputStream

class NewsDetailActivity: BaseActivity<NewsDetailViewModel, ActivityNewsDetailBinding>() {


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
    override fun initViews() = with(binding){




        Log.d("ActionMode", "Action mode started222??")
        val textView : TextView = binding.contentTextViewOfDetail
        textView.customSelectionActionModeCallback = object : ActionMode.Callback{
            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                Log.d("ActionMode", "Action mode started")
                // 기존 메뉴를 비우고 커스텀 메뉴 추가
                menu?.let{
                    menu.clear()
                    menu.add("추가")
                        .setOnMenuItemClickListener {
                            // 선택된 텍스트 가져오기
                            val selectedText = textView.text.substring(textView.selectionStart, textView.selectionEnd)
                            lifecycleScope.launch {
                                try{
                                    withContext(Dispatchers.IO){
                                        addToVocabulary(selectedText)
                                    }
                                    withContext(Dispatchers.Main) {
                                        Toast.makeText(this@NewsDetailActivity, "단어가 저장되었습니다!", Toast.LENGTH_SHORT).show()
                                    }

                                }catch (e:Exception){
                                    Log.e("Error", "작업 실패: ${e.message}")
                                    withContext(Dispatchers.Main) {
                                        Toast.makeText(this@NewsDetailActivity, "저장 중 오류 발생!", Toast.LENGTH_SHORT).show()
                                    }
                                }

                            }

                        mode?.finish() // 액션 모드 종료
                        true
                    }
                }

                return true
            }

            override fun onPrepareActionMode(p0: ActionMode?, p1: Menu?): Boolean {
                return true
            }

            override fun onActionItemClicked(p0: ActionMode?, p1: MenuItem?): Boolean {
                return true
            }

            override fun onDestroyActionMode(p0: ActionMode?) {

            }

        }
    }

    suspend fun addToVocabulary(textThatIWantToTranslate: String){
        //todo 번역 후, room에 저장

        viewModel.addButtonToVoca(textThatIWantToTranslate)


    }





    override fun observeData() = viewModel.currentNewsImLookingInDetailLiveData.observe(this@NewsDetailActivity) {
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
            //contentTextViewOfDetail.movementMethod = ScrollingMovementMethod.getInstance()

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

                viewModel.currentNewsImLookingInDetailLiveData.observe(this@NewsDetailActivity){

                    lifecycleScope.launch {
                        val temp = viewModel.isNewsScraped(it.newsUrl)
                        Log.d("fecbbcb",it.newsUrl)
                        Log.d("fecbbcb",temp.toString())
                        if(temp>0){
                            Toast.makeText(this@NewsDetailActivity, "이미 스크랩된 뉴스입니다.", Toast.LENGTH_SHORT).show()
                        } else{
                            Toast.makeText(this@NewsDetailActivity, "스크랩되었습니다", Toast.LENGTH_SHORT).show()
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