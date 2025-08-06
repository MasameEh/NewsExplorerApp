package com.example.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.domain.interfaces.INewsRepository
import com.example.domain.entities.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class SearchNewsUseCase @Inject constructor(
    private val newsRepository: INewsRepository
) {
    @RequiresApi(Build.VERSION_CODES.O)
    operator fun invoke(query: String): Flow<List<News>> = flow {
        val newsList = newsRepository.searchNews(query).news

        val sortedList = newsList.sortedByDescending { news ->
            runCatching {
                ZonedDateTime.parse(news.publishedAt, DateTimeFormatter.ISO_DATE_TIME)
            }.getOrElse {
                ZonedDateTime.now().minusYears(100)
            }
        }

        emit(sortedList)
    }
}