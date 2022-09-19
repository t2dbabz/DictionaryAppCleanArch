package com.example.dictionary.feature_dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dictionary.feature_dictionary.domain.model.Meaning
import com.example.dictionary.feature_dictionary.domain.model.WordInfo

@Entity
data class WorldInfoEntity(
    val word: String,
    val phonetic: String?,
    val meaning: List<Meaning>,
    val sourceUrls: List<String>,
    @PrimaryKey val id: Int? = null

) {
    fun toWordInfo(): WordInfo {
        return WordInfo(
            word = word,
            phonetic = phonetic,
            meaning = meaning,
           sourceUrls = sourceUrls,

        )
    }
}
