package com.myapplication.ui.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.myapplication.ui.screen.home.homeRoute
import com.myapplication.ui.screen.quoteView.quoteRoute

const val START_DESTINATION = "HOME_SCREEN"

@Composable
fun QuoteNavGraph(navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {
        homeRoute(navController)
        quoteRoute(navController)
    }
}