package com.myapplication.ui.screen.quoteView

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.ui.screen.home.QuoteImageUIState
import com.myapplication.ui.screen.home.toDomain
import domain.usecase.ManageFavoriteQuotesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuoteViewModel(
    private val favoriteQuotes: ManageFavoriteQuotesUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val args = QuoteArgs(savedStateHandle)

    private val _uiState = MutableStateFlow(QuoteImageUIState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(
                id = args.quoteId,
                imageURL = args.quoteURL,
                downloadLink = args.quoteDownloadLink,
            )
        }
        getSavedToFavorite(id = args.quoteId)
    }

    private fun saveFavoriteQuote(quoteImage: QuoteImageUIState) {
        viewModelScope.launch {
            _uiState.update { it.copy(isSaved = true) }
            favoriteQuotes.saveFavoriteQuote(quoteImage.toDomain())
        }
    }

    private fun removeFavoriteQuote(quoteId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isSaved = false) }
            favoriteQuotes.removeQuoteFromFavorite(id = quoteId)
        }
    }

    fun onClickFavoriteIcon() {
        if (_uiState.value.isSaved) {
            removeFavoriteQuote(_uiState.value.id)
        } else {
            saveFavoriteQuote(_uiState.value)
        }
    }

    private fun getSavedToFavorite(id: String) {
        viewModelScope.launch {
            val isSaved = favoriteQuotes.getFavoriteQuoteByID(id = id) != null
            _uiState.update { it.copy(isSaved = isSaved) }
        }
    }

}