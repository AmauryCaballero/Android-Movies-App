package com.larrykapija.moviesapp.ui.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.larrykapija.moviesapp.R
import com.larrykapija.moviesapp.ui.screens.components.InfiniteAnimation
import com.larrykapija.moviesapp.viewmodel.DetailsScreenViewModel

@Composable
fun DetailsScreen(
    navController: NavController,
    movieJson: String,
    viewModel: DetailsScreenViewModel = viewModel(LocalContext.current as ViewModelStoreOwner)
) {

    LaunchedEffect(movieJson) {
        viewModel.setMovieFromJson(movieJson)
    }

    val movie = viewModel.movie.collectAsState().value
    val details = viewModel.movieDetails.collectAsState().value

    if (movie != null) {
        Column(
            Modifier
                .fillMaxSize()
        ) {
            val screenHeight = LocalConfiguration.current.screenHeightDp
            val space = screenHeight / (100 / 30)

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://image.tmdb.org/t/p/original${movie.backdropPath}")
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.None,
                contentDescription = "backdrop image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(space.dp)
                    .aspectRatio(1.5f)
            )

            Column(
                Modifier
                    .padding(16.dp)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.Bottom),
                        text = "${movie.title} ",
                        style = MaterialTheme.typography.titleLarge.merge(MaterialTheme.colorScheme.onPrimary),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        modifier = Modifier
                            .align(Alignment.Bottom)
                            .padding(bottom = 6.dp),
                        text = movie.releaseDate ?: "",
                        style = MaterialTheme.typography.labelSmall.merge(MaterialTheme.colorScheme.onPrimary),
                        textAlign = TextAlign.Center
                    )

                }


                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp))


                Row {
                    if (details?.voteAverage != null) {
                        Text(
                            text = "${details.voteAverage}",
                            style = MaterialTheme.typography.bodyMedium.merge(MaterialTheme.colorScheme.onPrimary),
                            textAlign = TextAlign.Center
                        )

                        Text(
                            modifier = Modifier
                                .align(Alignment.CenterVertically),
                            text = "/ 10 ⭐ ",
                            style = MaterialTheme.typography.labelSmall.merge(MaterialTheme.colorScheme.onPrimary),
                        )
                    }

                    if (details?.runtime != null) {
                        Text(
                            text = "| ",
                            style = MaterialTheme.typography.bodyMedium.merge(MaterialTheme.colorScheme.onPrimary),
                            modifier = Modifier.align(Alignment.CenterVertically),
                        )

                        Text(
                            text = "${details.runtime}",
                            style = MaterialTheme.typography.labelSmall.merge(MaterialTheme.colorScheme.onPrimary),
                            modifier = Modifier.align(Alignment.CenterVertically),
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = movie.overview ?: "",
                    style = MaterialTheme.typography.bodySmall.merge(MaterialTheme.colorScheme.onPrimary)
                )
            }
        }
    } else {
        InfiniteAnimation(
            modifier = Modifier
                .fillMaxSize(),
            res = R.raw.error_animation
        )
    }
}