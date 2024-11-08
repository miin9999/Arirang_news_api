package diy.arirangnewsapi.model.news


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import diy.arirangnewsapi.data.entity.NewsDetailEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsDetailModel(
    @SerializedName("broadcast_date")
    var broadcastDate: String?,
    var content: String?,
    @SerializedName("news_url")
    var newsUrl: String?,
    @SerializedName("thum_url")
    var thumUrl: String?,
    var title: String?
):Parcelable{

    fun toEntity(): NewsDetailEntity {

        return NewsDetailEntity(
            broadcastDate = broadcastDate,
            content = content,
            newsUrl = newsUrl,
            thumUrl = thumUrl,
            title = title
        )
    }
}