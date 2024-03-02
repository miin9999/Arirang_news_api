package diy.arirangnewsapi.arirang_models


import com.google.gson.annotations.SerializedName

data class ArirangResponseModel(
    @SerializedName("items")
    var items: List<Item?>?,
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