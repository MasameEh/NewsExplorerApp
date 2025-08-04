package com.example.domain.entities

data class News(
    val source: Source,
    val author: String?,
    val title: String,
    val description: String?,
    val articleUrl: String,
    val imageUrl: String?,
    val publishedAt: String,
    val content: String?
)

data class Source(
    val id: String?,
    val name: String
)
