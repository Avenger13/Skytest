package com.test.skytest.data.network.api.search.response

data class Word(
    val id: Long,
    val text: String,
    val meanings: List<Meaning>
)