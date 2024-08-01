package com.siswanto.unsplash.model

data class Photo(
    val id: String,
    val urls: Urls
)

data class Urls(
    val small: String
)