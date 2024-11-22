package diy.arirangnewsapi.model.word

import android.os.Parcelable
import diy.arirangnewsapi.data.entity.WordEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class WordModel(
    var id: Long?,
    var originalWord:String,
    var translatedWord:String,

):Parcelable{

    companion object {
        fun toModel(entities: List<WordEntity?>?): List<WordModel> {
            return entities?.map { entity ->
                WordModel(
                    id = entity?.id,
                    originalWord = entity?.originalWord ?:"",
                    translatedWord = entity?.translatedWord ?:""
                )
            } ?: emptyList() // null 처리: entities가 null일 경우 빈 리스트 반환
        }
    }
}