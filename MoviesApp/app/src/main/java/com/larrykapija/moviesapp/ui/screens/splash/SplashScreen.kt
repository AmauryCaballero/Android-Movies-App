package com.larrykapija.moviesapp.ui.screens.splash

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.larrykapija.moviesapp.R
import com.larrykapija.moviesapp.ui.navigation.Destinations
import com.larrykapija.moviesapp.ui.theme.MoviesAppTheme

@Composable
fun SplashScreen(navController: NavController) {
    
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.splash_animation))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = 1,
        isPlaying = true,
        restartOnPlay = true
        )

    LaunchedEffect(key1 = progress) {
        if (progress == 1f) {
            navController.navigate(Destinations.MainScreen) {
                popUpTo(Destinations.SplashScreen) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun SplashScreenPreviewLight() {
    MoviesAppTheme {
        SplashScreen(rememberNavController())
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SplashScreenPreviewDark() {
    MoviesAppTheme {
        SplashScreen(rememberNavController())
    }
}