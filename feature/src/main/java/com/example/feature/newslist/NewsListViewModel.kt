package com.example.feature.newslist


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.ResponseState
import com.example.domain.usecase.GetLastSearchWordUseCase
import com.example.domain.usecase.SaveLastSearchWordUseCase
import com.example.domain.usecase.SearchNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "NewsListViewModel"

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val searchNewsUseCase: SearchNewsUseCase,
    private val saveLastSearchUseCase: SaveLastSearchWordUseCase,
    private val getLastSearchUseCase: GetLastSearchWordUseCase
) : ViewModel() {

    private val _mutableNewsList: MutableStateFlow<ResponseState> =
        MutableStateFlow(ResponseState.Idle)
    val newsList: StateFlow<ResponseState> = _mutableNewsList.asStateFlow()

    fun searchNews(category: String) {
        viewModelScope.launch {
            val result = searchNewsUseCase(category)
            Log.i(TAG, "searchNews: $category")
            _mutableNewsList.value = ResponseState.Loading
            result.catch {
                _mutableNewsList.value = ResponseState.Failure(it)
            }.collect { news ->
                _mutableNewsList.value = ResponseState.Success(news)
            }
        }
    }

    fun saveLastSearchWord(query: String) {
        saveLastSearchUseCase(query)
    }

    fun getLastSearchWord(): String? {
        return getLastSearchUseCase()
    }

    fun clearNewsList() {
        _mutableNewsList.value = ResponseState.Loading
    }

}