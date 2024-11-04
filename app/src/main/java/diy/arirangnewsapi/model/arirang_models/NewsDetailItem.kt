package diy.arirangnewsapi.model.arirang_models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsDetailItem(
    @SerializedName("broadcast_date")
    var broadcastDate: String?,
    @SerializedName("content")
    var content: String?,
    @SerializedName("news_url")
    var newsUrl: String?,
    @SerializedName("thum_url")
    var thumUrl: String?,
    @SerializedName("title")
    var title: String?
):Parcelable