package com.larrykapija.moviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.larrykapija.moviesapp.ui.navigation.Destinations
import com.larrykapija.moviesapp.ui.screens.main.MainScreen
import com.larrykapija.moviesapp.ui.screens.splash.SplashScreen
import com.larrykapija.moviesapp.ui.theme.MoviesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityContent()
        }
    }
}

@Composable
fun MainActivityContent() {
    AppNavigation()
}

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