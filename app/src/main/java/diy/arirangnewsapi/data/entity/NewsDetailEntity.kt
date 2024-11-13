package diy.arirangnewsapi.data.entity

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@androidx.room.Entity
data class NewsDetailEntity(

    var broadcastDate: String?,
    var content: String?,
    @PrimaryKey var newsUrl: String,
    var thumUrl: String?,
    var title: String?
):Parcelable
