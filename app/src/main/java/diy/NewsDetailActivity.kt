package diy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import diy.arirangnewsapi.R
import diy.arirangnewsapi.databinding.ActivityNewsDetailBinding

class NewsDetailActivity :AppCompatActivity() {

    lateinit var bindingofDetail : ActivityNewsDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingofDetail = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(bindingofDetail.root)




    }
}