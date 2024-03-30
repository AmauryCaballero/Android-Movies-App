package com.larrykapija.moviesapp.ui.screens.details

import androidx.compose.material3.Text
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.larrykapija.moviesapp.viewmodel.DetailsScreenViewModel

@Composable
fun DetailsScreen(
    navController: NavController,
    movieJson: String,
    viewModel: DetailsScreenViewModel = viewModel()) {

    LaunchedEffect(movieJson) {
        viewModel.setMovieFromJson(movieJson)
    }

    val movie = viewModel.movie.observeAsState().value

    Text("HELLOOOOO")

}