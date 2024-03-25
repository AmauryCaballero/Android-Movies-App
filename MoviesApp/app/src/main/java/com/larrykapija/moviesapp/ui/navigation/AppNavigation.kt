package com.larrykapija.moviesapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.larrykapija.moviesapp.ui.screens.main.MainScreen
import com.larrykapija.moviesapp.ui.screens.splash.SplashScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destinations.SplashScreen ) {
        composable(Destinations.SplashScreen) {
            SplashScreen(navController)
        }

        composable(Destinations.MainScreen) {
            MainScreen()
        }
    }
}