package com.example.domain.adapters

import com.example.domain.entities.NewsResponse

interface INewsRepository {
    suspend fun searchNews(query: String): NewsResponse
}