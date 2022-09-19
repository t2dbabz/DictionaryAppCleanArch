package com.example.dictionary.feature_dictionary.data.remote.dto


import com.example.dictionary.feature_dictionary.domain.model.Phonetic
import com.google.gson.annotations.SerializedName

data class PhoneticDTO(
    @SerializedName("audio")
    val audio: String,
    @SerializedName("license")
    val license: License,
    @SerializedName("sourceUrl")
    val sourceUrl: String,
    @SerializedName("text")
    val text: String
) {
    fun toPhonetic(): Phonetic {
        return Phonetic(
            audio = audio,
            license = license,
            sourceUrl = sourceUrl,
            text = text
        )
    }
}