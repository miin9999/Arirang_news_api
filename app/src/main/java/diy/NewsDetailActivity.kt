package diy

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import diy.arirangnewsapi.R
import diy.arirangnewsapi.databinding.ActivityNewsDetailBinding

class NewsDetailActivity :AppCompatActivity() {


    lateinit var bindingOfDetail : ActivityNewsDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingOfDetail = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(bindingOfDetail.root)



        val contentData = intent.getStringExtra("putContentData")
        val titleData = intent.getStringExtra("putTitleData")
        val thumbUrlData = intent.getStringExtra("putThumbUrl")

        bindingOfDetail.contentTextViewOfDetail.text = contentData
        bindingOfDetail.titleTextviewOfDetail.text = titleData

        // textView 에 스크롤바 달아주기 - content 밑 부분이 잘리는 현상 해결
        bindingOfDetail.contentTextViewOfDetail.movementMethod = ScrollingMovementMethod.getInstance()

        Glide
            .with(bindingOfDetail.imageViewOfDetail.context)
            .load(thumbUrlData)
            .into(bindingOfDetail.imageViewOfDetail)



    }
}