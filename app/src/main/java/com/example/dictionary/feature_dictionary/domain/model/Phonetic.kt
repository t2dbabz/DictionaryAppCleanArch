package com.example.dictionary.feature_dictionary.domain.model

import com.example.dictionary.feature_dictionary.data.remote.dto.License
import com.google.gson.annotations.SerializedName

data class Phonetic(
    val audio: String,

    val license: License,

    val sourceUrl: String,

    val text: String
)

