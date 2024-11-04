package diy.arirangnewsapi.model.arirang_models


import com.google.gson.annotations.SerializedName

data class NewsResponseModel(
    @SerializedName("items")
    var newsDetailItems: List<NewsDetailItem?>?,
    @SerializedName("numOfRows")
    var numOfRows: Int?,
    @SerializedName("pageNo")
    var pageNo: Int?,
    @SerializedName("resultCode")
    var resultCode: Int?,
    @SerializedName("resultMsg")
    var resultMsg: String?,
    @SerializedName("totalCount")
    var totalCount: Int?
)