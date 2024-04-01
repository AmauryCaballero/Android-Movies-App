package com.larrykapija.moviesapp.ui.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.larrykapija.moviesapp.network.response.Movie

@Composable
fun MoviesGrid(
    title: String,
    moviesList: List<Movie>,
    onMovieClicked: (Movie) -> Unit
) {
    Column(modifier = Modifier.padding(bottom = 50.dp)) {
        if (moviesList.isNotEmpty()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start
            )

            moviesList.take(6).chunked(3).forEach { row ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    row.forEach { movie ->
                        val shape = RoundedCornerShape(size = 30.dp)
                        AsyncImage(
                            model =  ImageRequest.Builder(LocalContext.current)
                                .data("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                            modifier = Modifier
                                .shadow(
                                    5.dp,
                                    shape = shape
                                )
                                .weight(1f)
                                .padding(4.dp)
                                .clip(shape)
                                .clickable {
                                    onMovieClicked(movie)
                                }
                        )
                    }
                }
            }
        }
    }
}