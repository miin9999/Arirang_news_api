package diy.arirangnewsapi.data.entity

import androidx.room.PrimaryKey


@androidx.room.Entity
data class WordEntity(
    @PrimaryKey override val id: Long?,
    var wordString:String
):Entity{




}

