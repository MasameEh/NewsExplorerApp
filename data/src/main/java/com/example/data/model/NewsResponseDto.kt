package com.example.data.model

import com.example.domain.entities.News
import com.example.domain.entities.NewsResponse
import com.example.domain.entities.Source
import com.google.gson.annotations.SerializedName


data class NewsResponseDto(
    val status: String,

    val totalResults: Int,

    @SerializedName("articles")
    val articles: List<NewsDto>
)

data class NewsDto(
    @SerializedName("source")
    val source: SourceDto,
    @SerializedName("author")
    val author: String?,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("url")
    val articleUrl: String,
    @SerializedName("urlToImage")
    val imageUrl: String?,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("content")
    val content: String?
)

data class SourceDto(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String
)

fun NewsResponseDto.toDomain(): NewsResponse {
    return NewsResponse(
        status = status,
        totalResults = totalResults,
        news = articles.map { it.toDomain() }
    )
}

fun NewsDto.toDomain(): News {
    return News(
        source = source.toDomain(),
        author = author,
        title = title,
        description = description,
        articleUrl = articleUrl,
        imageUrl = imageUrl,
        publishedAt = publishedAt,
        content = content
    )
}

fun SourceDto.toDomain(): Source {
    return Source(
        id = id,
        name = name
    )
}