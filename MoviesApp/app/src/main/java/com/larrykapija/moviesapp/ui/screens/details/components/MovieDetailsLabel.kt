package com.larrykapija.moviesapp.ui.screens.details.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.larrykapija.moviesapp.network.response.MovieDetails

@Composable
fun MovieDetailsLabel(details: MovieDetails?) {
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
                text = "${details.runtime} minutes",
                style = MaterialTheme.typography.labelSmall.merge(MaterialTheme.colorScheme.onPrimary),
                modifier = Modifier.align(Alignment.CenterVertically),
            )
        }
    }

}