package com.myapplication.ui.screen.quoteView

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.myapplication.R
import com.myapplication.ui.compose.ButtonIcon
import org.koin.androidx.compose.koinViewModel

@Composable
fun QuoteViewScreen(
    navController: NavController,
    viewModel: QuoteViewModel = koinViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(color = Color.Transparent)
        systemUiController.setNavigationBarColor(Color.Transparent)
    }
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
            modifier = modifier.fillMaxHeight(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp).padding(bottom = 48.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ButtonIcon(iconRes = R.drawable.heart, onClick = onClickShare)

            ButtonIcon(iconRes = R.drawable.share_icon, onClick = onClickShare)
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
