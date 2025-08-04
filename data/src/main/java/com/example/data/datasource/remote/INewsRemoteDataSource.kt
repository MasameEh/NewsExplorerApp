package com.example.data.datasource.remote

import com.example.domain.entities.NewsResponse

interface INewsRemoteDataSource {
    suspend fun searchNews(query: String): NewsResponse
}