package com.myapplication.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.myapplication.R

@Composable
fun QuoteButton(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 16.dp,
    text: String
) {
    Box(
        modifier = modifier
            .background(Color.Transparent)
            .clip(
                RoundedCornerShape(
                    topStart = cornerRadius,
                    topEnd = 0.dp,
                    bottomEnd = cornerRadius,
                    bottomStart = 0.dp
                )
            )
            .background(MaterialTheme.colorScheme.secondary),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = text,
            fontFamily = FontFamily(Font(resId = R.font.quote_font)),
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}