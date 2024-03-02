package diy.arirangnewsapi.arirang_models


import com.google.gson.annotations.SerializedName

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