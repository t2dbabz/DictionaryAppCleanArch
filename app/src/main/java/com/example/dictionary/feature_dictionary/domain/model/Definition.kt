package com.example.dictionary.feature_dictionary.domain.model

import com.google.gson.annotations.SerializedName

data class Definition(
    val antonyms: List<String>,
    val definition: String,
    val example: String?,
    val synonyms: List<String>
)
