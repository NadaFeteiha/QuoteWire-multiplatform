package com.myapplication.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapplication.R

@Composable
fun ManualPager(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    onRefresh: () -> Unit,
    isLoading: Boolean,
    error: String,
    isEndOfPager: Boolean,
    footerVisibility: Boolean = true,
    content: LazyGridScope.() -> Unit,
) {
    val scrollState = rememberLazyGridState()

    LazyVerticalGrid(
        modifier = modifier.fillMaxSize().background(backgroundColor),
        state = scrollState,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        content()

        if (footerVisibility) {
            item(
                span = {
                    val spanCount = 2
                    GridItemSpan(spanCount)
                }
            ) {
                PagerStatusItem(
                    isLoading = isLoading,
                    error = error,
                    isEndOfPager = isEndOfPager,
                    onClickTryAgain = onRefresh
                )
            }


        }
    }

    if (!scrollState.isScrollingUp() || !isEndOfPager) {
        LaunchedEffect(key1 = scrollState.isScrollInProgress) {
            if (!isLoading && !isEndOfPager) {
                onRefresh()
            }
        }
    }
}

@Composable
private fun LazyGridState.isScrollingUp(): Boolean {
    var previousIndex by remember(this) { mutableStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }
    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}

@Composable
fun PagerStatusItem(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    error: String,
    isEndOfPager: Boolean,
    onClickTryAgain: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        } else if (error.isNotEmpty()) {
            Text(
                modifier = Modifier.weight(1f),
                maxLines = 1,
                text = stringResource(id = R.string.no_internet)
            )
            Button(modifier = Modifier.weight(1f), onClick = { onClickTryAgain() }) {
                Text(
                    text = stringResource(id = R.string.try_again),
                    color = MaterialTheme.colorScheme.secondary
                )
            }

        } else if (isEndOfPager) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.no_data),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 14.sp
            )
        }
    }
}