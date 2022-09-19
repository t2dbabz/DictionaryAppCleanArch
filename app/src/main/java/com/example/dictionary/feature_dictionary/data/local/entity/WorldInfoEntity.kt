package com.example.dictionary.feature_dictionary.data.local.entity

import androidx.room.Entity
import com.example.dictionary.feature_dictionary.domain.model.Meaning
import com.example.dictionary.feature_dictionary.domain.model.Phonetic
import com.example.dictionary.feature_dictionary.domain.model.WordInfo

@Entity
data class WorldInfoEntity(
    val word: String,
    val phonetic: String,
    val meaning: List<Meaning>,
    val sourceUrls: List<String>,
    val id: Int? = null

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
