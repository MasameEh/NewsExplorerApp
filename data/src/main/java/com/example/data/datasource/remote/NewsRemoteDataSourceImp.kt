package com.example.data.datasource.remote

import com.example.data.model.toDomain
import com.example.domain.entities.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRemoteDataSourceImp @Inject constructor(
    private val apiService: NewsApiService
): INewsRemoteDataSource {

    override suspend fun searchNews(query: String): NewsResponse = withContext(Dispatchers.IO) {
        apiService.searchNews(query).toDomain()
    }

}