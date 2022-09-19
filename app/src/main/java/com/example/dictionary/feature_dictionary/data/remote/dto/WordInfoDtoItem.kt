package com.example.dictionary.feature_dictionary.data.remote.dto


import com.example.dictionary.feature_dictionary.domain.model.WordInfo
import com.google.gson.annotations.SerializedName

data class WordInfoDtoItem(
    @SerializedName("license")
    val license: License,

    @SerializedName("meanings")
    val meaningDTOS: List<MeaningDTO>,

    @SerializedName("phonetic")
    val phonetic: String,

    @SerializedName("phonetics")
    val phoneticDTOS: List<PhoneticDTO>,

    @SerializedName("sourceUrls")
    val sourceUrls: List<String>,

    @SerializedName("word")
    val word: String
) {
    fun toWordInfo(): WordInfo {
        return WordInfo(
            license = license,
            meaning = meaningDTOS.map { it.toMeaning() },
            phonetic = phonetic,
            sourceUrls =sourceUrls,
            word = word
        )
    }
}