package com.myapplication.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.usecase.GetImagesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val images: GetImagesUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState = _uiState.asStateFlow()

    init {
        getInitData()
    }

    fun getInitData() {
        _uiState.tryEmit(HomeUIState())
        viewModelScope.launch {
            try {
                val quotes = images()
                _uiState.update { it.copy(images = quotes.toUIState(), isLoading = false) }
            } catch (t: Throwable) {
                _uiState.update { it.copy(error = t.toString(), isLoading = false) }
            }
        }
    }

    fun getMoreQuotes() {
        _uiState.update { it.copy(isPagerLoading = true, pagerError = "") }
        viewModelScope.launch {
            try {
                val quotes = images.getMoreImages()
                _uiState.update {
                    it.copy(
                        images = it.images + quotes.toUIState(),
                        isPagerLoading = false
                    )
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(pagerError = t.toString(), isPagerLoading = false) }
            }
        }
    }

}