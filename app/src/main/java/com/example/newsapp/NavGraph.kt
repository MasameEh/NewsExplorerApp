package com.example.newsapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.feature.detail.NewsDetailsWebViewScreen
import com.example.feature.newslist.NewsListScreen


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavHostSetUp(navHostController: NavHostController){

    NavHost(
        navController = navHostController,
        startDestination = ScreensRoute.NewsListScreen
    ){

        composable<ScreensRoute.NewsListScreen> {
            NewsListScreen(
                onClick = { url ->
                    navHostController.navigate(ScreensRoute.NewsDetailsWebViewScreen(url))
                },
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