package com.example.feature.newslist

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.unit.dp
import com.example.domain.entities.News
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.utils.ResponseState


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsListScreen(
    onClick: (String) -> Unit,
    viewModel: NewsListViewModel = hiltViewModel(),
) {

    val newsState by viewModel.newsList.collectAsStateWithLifecycle()
    var searchQuery by remember { mutableStateOf("") }

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val word = viewModel.getLastSearchWord()
        if (!word.isNullOrBlank()) {
            viewModel.searchNews(word)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search news...") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                singleLine = true,
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                )
            )

            Button(
                onClick = {
                    if (searchQuery.isNotBlank()) {
                        viewModel.searchNews(searchQuery)
                        viewModel.saveLastSearchWord(searchQuery)
                    }else{
                        Toast.makeText(
                            context,
                            "Please enter a search term",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text("Search")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        when (newsState) {
            is ResponseState.Idle -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Search for news above 👆")
                }
            }
            is ResponseState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is ResponseState.Failure -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Error loading news")
                }
            }

            is ResponseState.Success -> {
                val news = (newsState as ResponseState.Success).data as List<News>
                NewsList(
                    onClick = onClick,
                    news = news,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsList(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
    news: List<News>,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 8.dp
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(news.size) {
            NewsItem(
                onClick = onClick,
                news = news[it]
            )
        }
    }
}







