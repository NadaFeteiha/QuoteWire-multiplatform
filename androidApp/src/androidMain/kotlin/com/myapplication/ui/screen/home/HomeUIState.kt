package com.myapplication.ui.screen.home

import domain.models.QuoteImage


data class HomeUIState(
    val images: List<QuoteImageUIState> = emptyList()
)

data class QuoteImageUIState(
    val id: String = "",
    val imageURL: String = "",
    val downloadLink: String = ""
)

fun List<QuoteImage>.toUIState() = map { it.toUIState() }

fun QuoteImage.toUIState(): QuoteImageUIState {
    return QuoteImageUIState(
        id = id,
        imageURL = imageURL,
        downloadLink = downloadLink,
    )
}
