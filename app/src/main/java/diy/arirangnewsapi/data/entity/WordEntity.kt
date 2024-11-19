package diy.arirangnewsapi.data.entity

import androidx.room.PrimaryKey


@androidx.room.Entity
data class WordEntity(
    @PrimaryKey(autoGenerate = true) override val id: Long? = null,
    var originalWord:String,
    var translatedWord:String
):Entity{




}

