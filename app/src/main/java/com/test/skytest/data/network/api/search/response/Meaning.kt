package com.test.skytest.data.network.api.search.response

data class Meaning(
    val id: Long,
    val partOfSpeechCode: String,
    val translation: Translation,
    val previewUrl: String,
    val imageUrl: String,
    val transcription: String,
    val soundUrl: String
)