package com.myapplication.ui.screen.quoteView

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.myapplication.ui.screen.home.QuoteImageUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class QuoteViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val args = QuoteArgs(savedStateHandle)

    private val _uiState = MutableStateFlow(QuoteImageUIState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(
                id = args.quoteId,
                imageURL = args.quoteURL,
                downloadLink = args.quoteDownloadLink
            )
        }
    }
}