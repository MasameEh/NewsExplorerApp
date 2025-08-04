package com.example.data.repository

import com.example.data.datasource.remote.INewsRemoteDataSource
import com.example.domain.adapters.INewsRepository
import com.example.domain.entities.NewsResponse
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: INewsRemoteDataSource
): INewsRepository {

    override suspend fun searchNews(query: String): NewsResponse {
        return remoteDataSource.searchNews(query)
    }
}