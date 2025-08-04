package com.example.newsapp

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.feature.detail.NewsDetailsWebViewScreen
import com.example.feature.newslist.NewsListScreen
import com.example.feature.search.SearchScreen

private const val TAG = "NavGraph"

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainActivity.NavHostSetUp(){

    NavHost(
        navController = navHostController,
        startDestination = ScreensRoute.SearchScreen
    ){
        composable<ScreensRoute.SearchScreen> {
            SearchScreen(
                onSearch = { category ->
                    Log.i(TAG, "SearchScreen: $category")
                    navHostController.navigate(ScreensRoute.NewsListScreen(category))
                },
            )
        }

        composable<ScreensRoute.NewsListScreen> { backStackEntry ->
            val entry = backStackEntry.toRoute<ScreensRoute.NewsListScreen>()

            NewsListScreen(
                onClick = { url ->
                    navHostController.navigate(ScreensRoute.NewsDetailsWebViewScreen(url))
                },
                category = entry.category
            )
        }

        composable<ScreensRoute.NewsDetailsWebViewScreen> { backStackEntry ->
            val entry = backStackEntry.toRoute<ScreensRoute.NewsDetailsWebViewScreen>()
            NewsDetailsWebViewScreen(
                entry.url
            )
        }
    }
}