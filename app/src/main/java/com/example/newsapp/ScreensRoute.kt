package com.example.newsapp

import kotlinx.serialization.Serializable


@Serializable
sealed class ScreensRoute {
    @Serializable
    object SearchScreen : ScreensRoute()
    @Serializable
    data class NewsDetailsWebViewScreen(val url: String) : ScreensRoute()
    @Serializable
    data class NewsListScreen(val category: String) : ScreensRoute()
}