package diy.arirangnewsapi.screen.main.myword.detail

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import diy.arirangnewsapi.data.entity.NewsDetailEntity
import diy.arirangnewsapi.databinding.ActivityWordDetailBinding
import diy.arirangnewsapi.model.word.WordModel
import diy.arirangnewsapi.screen.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class WordDetailActivity: BaseActivity<WordDetailViewModel,ActivityWordDetailBinding>() {

    companion object{
        fun newIntent(context: Context, wordModel: WordModel) =
            Intent(context, WordDetailActivity::class.java).apply {
                putExtra("word_item", wordModel)
            }
    }

    override val viewModel by viewModel<WordDetailViewModel> {
        parametersOf(
            intent.getParcelableExtra<WordModel>("word_item")
        )
    }

    override fun getViewBinding(): ActivityWordDetailBinding = ActivityWordDetailBinding.inflate(layoutInflater)

    override fun observeData() = viewModel.currentWordImLookingLiveData.observe(this@WordDetailActivity){

        with(binding){
            originalWordTextView.text = it.originalWord
            translatedWordTextView.text = it.translatedWord

            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeButtonEnabled(true)
            supportActionBar?.title = ""
        }
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