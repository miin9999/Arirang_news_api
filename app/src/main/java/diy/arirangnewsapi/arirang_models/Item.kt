package diy.arirangnewsapi.arirang_models


import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Item(
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
)