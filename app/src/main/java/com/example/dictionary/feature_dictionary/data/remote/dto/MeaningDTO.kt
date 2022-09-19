package com.example.dictionary.feature_dictionary.data.remote.dto


import com.example.dictionary.feature_dictionary.domain.model.Meaning
import com.google.gson.annotations.SerializedName

data class MeaningDTO(
    @SerializedName("antonyms")
    val antonyms: List<String>,
    @SerializedName("definitions")
    val definitions: List<DefinitionDTO>,
    @SerializedName("partOfSpeech")
    val partOfSpeech: String,
    @SerializedName("synonyms")
    val synonyms: List<String>
) {
    fun toMeaning(): Meaning {
        return Meaning(
            antonyms = antonyms,
            definitions = definitions.map { it.toDefinition() },
            partOfSpeech = partOfSpeech,
            synonyms = synonyms
        )
    }
}