package diy.arirangnewsapi.screen.main.myword.detail

import android.content.Context
import android.content.Intent
import diy.arirangnewsapi.databinding.ActivityWordDetailBinding
import diy.arirangnewsapi.model.word.WordModel
import diy.arirangnewsapi.screen.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class WordDetailActivity :BaseActivity<WordDetailViewModel,ActivityWordDetailBinding>() {

    companion object{
        fun newIntent(context: Context, wordModel: WordModel) =
            Intent(context, WordDetailActivity::class.java).apply {
                putExtra("word_item", wordModel)
            }
    }

    override val viewModel by viewModel<WordDetailViewModel>()

    override fun getViewBinding(): ActivityWordDetailBinding= ActivityWordDetailBinding.inflate(layoutInflater)

    override fun observeData(){}

}