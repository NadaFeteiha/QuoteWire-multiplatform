package com.myapplication.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.myapplication.R
import com.myapplication.ui.compose.ErrorLottieAnimations
import com.myapplication.ui.compose.LottieAnimations
import com.myapplication.ui.compose.ManualPager
import com.myapplication.ui.compose.QuoteButton
import com.myapplication.ui.compose.RoundedSquare
import com.myapplication.ui.compose.TopAppBar
import com.myapplication.ui.modifiers.bouncingClickable
import com.myapplication.ui.screen.quoteView.navigateToQuote
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.koinViewModel
import ui.modifiers.nonRippleEffect

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val systemUIController = rememberSystemUiController()
    systemUIController.setStatusBarColor(color = MaterialTheme.colorScheme.background)

    Column(
        modifier = Modifier.fillMaxSize().padding(bottom = 45.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {

        TopAppBar(titleRes = R.string.app_name)

        if (uiState.error.isNotEmpty()) {
            ErrorLottieAnimations(onClickTryAgain = viewModel::getInitData)
        } else if (uiState.isLoading) {
            LottieAnimations(lottieRes = R.raw.loading)
        } else {
            ManualPager(
                onRefresh = viewModel::getMoreQuotes,
                isLoading = uiState.isPagerLoading,
                error = uiState.pagerError,
                isEndOfPager = uiState.isEndOfPager,
            ) {
                items(uiState.images) { quote ->
                    RoundedSquare(
                        modifier = Modifier.bouncingClickable { navController.navigateToQuote(quote) },
                        imageUrl = quote.imageURL,
                        cornerRadius = 25.dp
                    )
                }
            }
        }
    }
}
