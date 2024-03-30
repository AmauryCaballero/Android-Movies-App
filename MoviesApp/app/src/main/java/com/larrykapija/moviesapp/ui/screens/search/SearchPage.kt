package com.larrykapija.moviesapp.ui.screens.search

import android.content.res.Configuration
import androidx.annotation.RawRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.larrykapija.moviesapp.R
import com.larrykapija.moviesapp.network.response.Movie
import com.larrykapija.moviesapp.network.response.toJson
import com.larrykapija.moviesapp.ui.navigation.Destinations
import com.larrykapija.moviesapp.ui.screens.search.components.SearchMovieItem
import com.larrykapija.moviesapp.ui.theme.MoviesAppTheme
import com.larrykapija.moviesapp.viewmodel.SearchPageViewModel
import com.larrykapija.moviesapp.viewmodel.SearchState
import kotlinx.coroutines.delay
import java.net.URLEncoder


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchPage(
    navController: NavController,
    innerPadding: PaddingValues,
    viewModel: SearchPageViewModel = viewModel(LocalContext.current as ViewModelStoreOwner)
) {

    val uiState by viewModel.uiState.observeAsState(SearchState.Empty)

    Column(modifier = Modifier.padding(innerPadding)) {
        var searchText by remember { mutableStateOf("") }
        val debouncedSearchText = remember { mutableStateOf(searchText) }

        TextField(
            value = searchText,
            onValueChange = {
                searchText = it
            },
            label = { Text("Search movies") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        LaunchedEffect(searchText) {
            delay(500) // Debounce
            debouncedSearchText.value = searchText
        }

        LaunchedEffect(debouncedSearchText.value) {
            viewModel.searchMovies(debouncedSearchText.value)
        }

        when (val currentState = uiState) {
            is SearchState.Loading ->
                InfiniteAnimation(
                    modifier = Modifier.fillMaxSize(),
                    res = R.raw.loading_animation
                )

            is SearchState.Success -> {
                LazyColumn {
                    items(currentState.movies.results.filter { movie ->  movie.posterPath != null}) { movie ->
                        SearchMovieItem(
                            modifier = Modifier
                                .clickable {
                                    val movieJson = movie.toJson()
                                    val encodedMovie = URLEncoder.encode(movieJson, "UTF-8")
                                    navController.navigate("${Destinations.DetailsScreen}/${encodedMovie}")
                                },
                            movie = movie
                        )
                    }
                }
            }

            is SearchState.Error ->
                InfiniteAnimation(
                    modifier = Modifier.fillMaxSize(),
                    res = R.raw.error_animation
                )

            is SearchState.Empty ->
                InfiniteAnimation(
                    modifier = Modifier.fillMaxSize(),
                    res = R.raw.empty_animation
                )
        }
    }
}

@Composable
private fun InfiniteAnimation(
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SearchPagePreviewLight() {
    MoviesAppTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            SearchBar(
                query = "",
                onQueryChange = { },
                onSearch = { },
                active = true,
                onActiveChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

            }
        }
    }
}