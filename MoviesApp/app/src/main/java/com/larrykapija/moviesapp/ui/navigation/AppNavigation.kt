package com.larrykapija.moviesapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.larrykapija.moviesapp.ui.screens.details.DetailsScreen
import com.larrykapija.moviesapp.ui.screens.main.MainScreen
import com.larrykapija.moviesapp.ui.screens.splash.SplashScreen
import java.net.URLDecoder


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destinations.SplashScreen ) {
        composable(Destinations.SplashScreen) {
            SplashScreen(navController)
        }

        composable(Destinations.MainScreen) {
            MainScreen(navController)
        }

        composable(
            "${Destinations.DetailsScreen}/{movie}",
            arguments = listOf(navArgument("movie") { type = NavType.StringType})
        ) {
            DetailsScreen(
                navController = navController,
                movieJson = URLDecoder.decode(it.arguments?.getString("movie"), "UTF-8") // Usa "movie", no "movieJson"
            )
        }
    }
}