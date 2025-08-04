package com.example.feature.newslist

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.unit.dp
import com.example.domain.entities.News
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import com.example.core.utils.ResponseState
import com.example.core.utils.toRelativeTime


private const val TAG = "NewsList"
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsListScreen(
    onClick: (String) -> Unit,
    viewModel: NewsListViewModel = hiltViewModel(),
    category: String
){

    val newsState by viewModel.newsList.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.searchNews(category)
    }


    when (newsState) {
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


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsItem(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
    news: News,
) {
    Card(
        modifier = modifier,
        onClick = {
            onClick(news.articleUrl)
        },
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        )
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            NewsImage(
                imageUrl = news.imageUrl ?: "",
                contentDescription = news.title,
                modifier =
                Modifier
                    .height(100.dp)
                    .fillMaxWidth(.32f)
            )

            Column(
                modifier =
                Modifier
                    .padding(end = 8.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = news.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = news.publishedAt.toRelativeTime(),
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = news.source.name,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

@Composable
fun NewsImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = imageUrl,
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        loading = {
            Box(
                modifier =
                Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    strokeWidth = 2.dp
                )
            }
        },
        error = {
            Box(
                modifier =
                Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(48.dp),
                    imageVector = Icons.Outlined.Image,
                    contentDescription = "Image not available",
                    tint = MaterialTheme.colorScheme.onBackground.copy(
                        alpha = .5f
                    )
                )
            }
        }
    )
}


