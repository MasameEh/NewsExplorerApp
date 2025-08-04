package com.example.domain.usecase

import com.example.domain.adapters.INewsRepository
import com.example.domain.entities.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchNewsUseCase @Inject constructor(private val newsRepository: INewsRepository) {

     operator fun invoke(query: String): Flow<List<News>> = flow {
         emit(newsRepository.searchNews(query).news)
    }

}