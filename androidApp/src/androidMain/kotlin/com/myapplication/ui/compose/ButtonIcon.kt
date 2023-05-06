package com.myapplication.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ui.modifiers.nonRippleEffect

@Composable
fun ButtonIcon(
    modifier: Modifier = Modifier,
    description: String = "",
    onClick: () -> Unit,
    cornerRadius: Dp = 16.dp,
    iconRes: Int
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
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(id = iconRes) ,
            contentDescription = description,
            modifier = modifier.size(48.dp).padding(8.dp).nonRippleEffect { onClick() },
            tint = MaterialTheme.colorScheme.primary
        )
    }
}