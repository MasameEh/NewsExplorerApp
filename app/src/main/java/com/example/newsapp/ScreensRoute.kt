package com.example.newsapp

import kotlinx.serialization.Serializable


@Serializable
sealed class ScreensRoute {

    @Serializable
    data class NewsDetailsWebViewScreen(val url: String) : ScreensRoute()

    @Serializable
    data object NewsListScreen : ScreensRoute()
}