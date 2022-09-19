package com.example.dictionary.feature_dictionary.domain.model

import com.example.dictionary.feature_dictionary.data.remote.dto.License
import com.example.dictionary.feature_dictionary.data.remote.dto.MeaningDTO
import com.example.dictionary.feature_dictionary.data.remote.dto.PhoneticDTO
import com.google.gson.annotations.SerializedName

data class WordInfo(

    val meaning: List<Meaning>,

    val phonetic: String?,

    val sourceUrls: List<String>,

    val word: String
)
