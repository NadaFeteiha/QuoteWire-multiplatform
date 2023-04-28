package com.myapplication.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun RoundedSquare(
    modifier: Modifier = Modifier,
    color: Color = Color.Transparent,
    cornerRadius: Dp = 0.dp,
    imageUrl: String,
) {
    Box(
        modifier = modifier
            .background(color)
            .clip(
                RoundedCornerShape(
                    topStart = cornerRadius,
                    topEnd = 0.dp,
                    bottomEnd = cornerRadius,
                    bottomStart = 0.dp
                )
            ),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = "Leaf image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )
    }
}
