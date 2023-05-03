package com.myapplication.ui.screen.quoteView

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


private const val ROUTE_QUOTE = "QuoteRoute"

fun NavController.navigateToQuote(id: String, quoteUrl: String, quoteDownloadLink: String) {
    val quoteUrl = URLEncoder.encode(quoteUrl, StandardCharsets.UTF_8.toString())
    val quoteDownloadLink = URLEncoder.encode(quoteDownloadLink, StandardCharsets.UTF_8.toString())
    navigate("$ROUTE_QUOTE/$id/$quoteUrl/$quoteDownloadLink")
}

fun NavGraphBuilder.quoteRoute(navController: NavController) {
    composable(
        route = "$ROUTE_QUOTE/{${QuoteArgs.Quote_ID_ARG}}/{${QuoteArgs.Quote_URL_ARG}}/{${QuoteArgs.Quote_DOWNLOAD_ARG}}",
        arguments = listOf(
            navArgument(QuoteArgs.Quote_ID_ARG) { NavType.StringType },
            navArgument(QuoteArgs.Quote_URL_ARG) { NavType.StringType },
            navArgument(QuoteArgs.Quote_DOWNLOAD_ARG) { NavType.StringType }
        )
    ) {
        QuoteViewScreen(navController)
    }
}