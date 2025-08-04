package com.example.data.datasource.remote

import com.example.data.model.NewsResponseDto
import com.example.domain.entities.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String = "d4bf15535d644684b1b3e6e44b7ee0f6"
    ): NewsResponseDto
}