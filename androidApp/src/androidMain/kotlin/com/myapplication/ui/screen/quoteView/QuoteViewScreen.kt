package com.myapplication.ui.screen.quoteView

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import org.koin.androidx.compose.koinViewModel
import ui.modifiers.nonRippleEffect

@Composable
fun QuoteViewScreen(
    navController: NavController,
    viewModel: QuoteViewModel = koinViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    QuoteViewContent(
        imageUrl = state.imageURL,
        onClickShare = { shareImage(state.downloadLink, context = context) }
    )
}

@Composable
fun QuoteViewContent(
    modifier: Modifier = Modifier,
    imageUrl: String,
    onClickShare: () -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = rememberAsyncImagePainter(model = imageUrl),
            contentDescription = "Leaf image",
            modifier = modifier
                .fillMaxHeight(),
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Share,
                contentDescription = "Share",
                modifier = Modifier.nonRippleEffect { onClickShare() },
//                tint = MaterialTheme.colorScheme.primary
            )
            Icon(Icons.Filled.Favorite, contentDescription = "Save")
        }
    }
}

private fun shareImage(url: String, context: Context) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, "Image URL")
        putExtra(Intent.EXTRA_TEXT, url)
    }
    val shareIntent = Intent.createChooser(intent, null)
    context.startActivity(shareIntent)
}

@Composable
@Preview
fun QuoteViewPreview() {
    QuoteViewContent(imageUrl = "", onClickShare = {})
}
