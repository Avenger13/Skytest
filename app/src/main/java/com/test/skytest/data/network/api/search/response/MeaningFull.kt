package com.test.skytest.data.network.api.search.response

data class MeaningFull(
    val id: String,
    val wordId: Long,
    val difficultyLevel: Int,
    val partOfSpeechCode: String,
    val prefix: String,
    val text: String,
    val soundUrl: String,
    val transcription: String,
    val properties: Properties,
    val updatedAt: String,
    val mnemonics: String,
    val translation: Translation,
    val images: List<Image>,
    val definition: Definition,
    val examples: List<Example>,
    val meaningsWithSimilarTranslation: List<MeaningsWithSimilarTranslation>,
    val alternativeTranslations: List<AlternativeTranslation>
) {
    data class Properties(
        val collocation: Boolean,
        val countability: String,
        val irregularPlural: Boolean,
        val falseFriends: List<Any>
    )


    data class Image(
        val url: String
    )

    data class Definition(
        val text: String,
        val soundUrl: String
    )

    data class Example(
        val text: String,
        val soundUrl: String
    )

    data class MeaningsWithSimilarTranslation(
        val meaningId: Long,
        val frequencyPercent: String,
        val partOfSpeechAbbreviation: String,
        val translation: Translation
    )

    data class AlternativeTranslation(
        val text: String,
        val translation: Translation
    )
}