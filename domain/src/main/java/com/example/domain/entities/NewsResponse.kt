package com.example.domain.entities

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val news: List<News>
)