package com.myapplication.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.koinViewModel
import com.myapplication.ui.compose.RoundedSquare
import ui.modifiers.nonRippleEffect

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    onQuoteClicked: (String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    val state = remember { mutableStateOf(HomeUIState()) }

    LaunchedEffect(Unit) {
        viewModel.uiState.onEach {
            state.value = it
        }.launchIn(this)
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(uiState.images) { quote ->
            RoundedSquare(
                modifier = Modifier.nonRippleEffect { onQuoteClicked(quote.imageURL) },
                imageUrl = quote.imageURL,
                cornerRadius = 25.dp
            )
        }
    }
}
