package com.larrykapija.moviesapp.ui.screens.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition


@Composable
fun InfiniteAnimation(
    modifier: Modifier,
    res: Int
) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(res))
    val progress by animateLottieCompositionAsState(
        composition,
        isPlaying = true,
        restartOnPlay = true,
        iterations = Int.MAX_VALUE
    )

    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier
    )
}