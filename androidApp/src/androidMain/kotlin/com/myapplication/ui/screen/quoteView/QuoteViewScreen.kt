package com.myapplication.ui.screen.quoteView

import android.Manifest
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.myapplication.R
import com.myapplication.ui.compose.ButtonIcon
import com.myapplication.ui.utils.shareImage
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

    val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    val downloadImage =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(context, "Downloading...", Toast.LENGTH_LONG).show()
                val request = DownloadManager.Request(Uri.parse(state.downloadLink))
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "image.jpg")
                downloadManager.enqueue(request)
                Toast.makeText(context, "Done...", Toast.LENGTH_LONG).show()
            }
        }

    QuoteViewContent(
        imageUrl = state.imageURL,
        onClickShare = { shareImage(state.downloadLink, context = context) },
        onClickDownload = {
            // TODO: enable download until Done downloading..
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
                // Request MANAGE_EXTERNAL_STORAGE permission for Android 11 and higher
                downloadImage.launch(Manifest.permission.READ_MEDIA_IMAGES)
            } else {
                // Request WRITE_EXTERNAL_STORAGE permission for Android 10 and lower
                downloadImage.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }
    )
}

@Composable
fun QuoteViewContent(
    modifier: Modifier = Modifier,
    imageUrl: String,
    onClickShare: () -> Unit,
    onClickDownload: () -> Unit
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

            ButtonIcon(iconRes = R.drawable.download_icon, onClick = onClickDownload)

        }
    }
}
