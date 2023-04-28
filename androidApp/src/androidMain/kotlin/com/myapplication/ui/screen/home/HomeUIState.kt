package com.myapplication.ui.screen.home

import domain.models.QuoteImage


data class HomeUIState(
    val output: String = "",
    val images: List<QuoteImage> = emptyList()
)