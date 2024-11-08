package diy.arirangnewsapi.model.news


import com.google.gson.annotations.SerializedName

data class NewsResponseModel(
    @SerializedName("items")
    var newsDetailModels: List<NewsDetailModel?>?,
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