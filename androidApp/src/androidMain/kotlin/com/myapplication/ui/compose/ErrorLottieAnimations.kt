package com.myapplication.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.myapplication.R
import com.myapplication.ui.modifiers.nonRippleEffect

@Composable
fun ErrorLottieAnimations(
    repeatCount: Int = 100,
    onClickTryAgain: () -> Unit
) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.error))

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LottieAnimation(composition = composition, iterations = repeatCount)

        QuoteButton(modifier = Modifier.nonRippleEffect(onClickTryAgain), text = "Try again!")
    }
}