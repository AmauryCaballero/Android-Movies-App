package com.larrykapija.moviesapp.ui.screens.splash

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.larrykapija.moviesapp.ui.navigation.Destinations
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(key1 = true) {
        delay(1000)
        navController.navigate(Destinations.MainScreen) {
            popUpTo(Destinations.SplashScreen) { inclusive = true }
        }
    }

    Text(text = "Splash Scren")
}