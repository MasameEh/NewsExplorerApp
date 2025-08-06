package com.example.domain.interfaces

import com.example.domain.entities.NewsResponse

interface INewsRepository {
    suspend fun searchNews(query: String): NewsResponse
}