package com.larrykapija.moviesapp.ui.screens.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.larrykapija.moviesapp.network.response.Movie


@Composable
fun SearchMovieItem(movie: Movie) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clip(RoundedCornerShape(8.dp))
        .background(MaterialTheme.colorScheme.surface)
        .padding(8.dp)) {

        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .offset(x = 20.dp, y = 10.dp)
                .fillMaxWidth()
                .height(70.dp)
                .background(MaterialTheme.colorScheme.tertiary)
                .clip(RoundedCornerShape(10.dp))
        )

        Row(
            modifier = Modifier
                .align(Alignment.TopStart)
        ) {
            movie.posterPath.let {
                Box(
                    modifier = Modifier
                        .shadow(5.dp, RoundedCornerShape(10.dp))
                        .clip(RoundedCornerShape(10.dp))
                ) {
                    val posterPath: String = "https://image.tmdb.org/t/p/w500${it}"

                    AsyncImage(
                        model = posterPath,
                        contentDescription = movie.title,
                        modifier = Modifier
                            .height(100.dp)
                    )
                }
            }

            Spacer(Modifier.width(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                movie.title?.let {
                    Text(modifier = Modifier.align(Alignment.Start),
                        text = it,
                        style = MaterialTheme.typography.titleSmall
                    )
                }

                movie.overview?.let {
                    val text = if (it.length > 100) it.substring(0,100) + "..." else it
                    Text(text = text, style = MaterialTheme.typography.bodySmall )
                }
            }
        }
    }
}
