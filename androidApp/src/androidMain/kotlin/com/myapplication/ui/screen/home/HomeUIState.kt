package com.myapplication.ui.screen.home

import domain.models.QuoteImage


data class HomeUIState(
    val images: List<QuoteImageUIState> = emptyList(),
    val isLoading: Boolean = true,
    val isPagerLoading: Boolean = false,
    val isEndOfPager: Boolean = false,
    val pagerError: String = "",
    val error: String = ""
)

data class QuoteImageUIState(
    val id: String = "",
    val imageURL: String = "",
    val downloadLink: String = "",
    val isSaved: Boolean = false
)

fun List<QuoteImage>.toUIState() = map { it.toUIState() }

fun QuoteImage.toUIState(): QuoteImageUIState {
    return QuoteImageUIState(
        id = id,
        imageURL = imageURL,
        downloadLink = downloadLink,
    )
}

fun QuoteImageUIState.toDomain(): QuoteImage {
    return QuoteImage(
        id = id,
        imageURL = imageURL,
        downloadLink = downloadLink,
    )
}
