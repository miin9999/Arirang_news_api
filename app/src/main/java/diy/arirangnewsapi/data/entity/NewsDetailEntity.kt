package diy.arirangnewsapi.data.entity

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@androidx.room.Entity
data class NewsDetailEntity(

    @PrimaryKey(autoGenerate = true)
    override val id: Long? = null,
    var broadcastDate: String?,
    var content: String?,
    var newsUrl: String?,
    var thumUrl: String?,
    var title: String?
):Entity,Parcelable
