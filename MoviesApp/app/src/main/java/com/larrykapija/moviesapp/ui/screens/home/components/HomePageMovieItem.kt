package com.larrykapija.moviesapp.ui.screens.home.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.larrykapija.moviesapp.network.response.Movie

@Composable
fun HomePageMovieItem(
    movie: Movie,
    index: Int,
    focusedItemIndex: Int,
    onClick: () -> Unit
) {
    val isFocused = index == focusedItemIndex

    val rotationDegrees by animateFloatAsState(targetValue = when {
        index < focusedItemIndex -> -12f
        index > focusedItemIndex -> 12f
        else -> 0f
    }, animationSpec = TweenSpec(
        durationMillis = 750
    ),
        label = "movie item rotation animation"
    )

    val alpha by animateFloatAsState(targetValue = if (isFocused) 1f else 0.8f, label = "movie item alpha animation")
    val scale by animateFloatAsState(targetValue = if (isFocused) 1f else 0.8f, label = "movie item scale animation")
    val bottomPadding by animateDpAsState(
        targetValue = if (isFocused) 100.dp else 0.dp,
        animationSpec = TweenSpec(
            durationMillis = 500,
            easing =  FastOutSlowInEasing
        ), label = "movie item bottom padding animation"
    )

    val shape = RoundedCornerShape(size = 30.dp)

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://image.tmdb.org/t/p/w500${movie.posterPath}")
            .crossfade(true)
            .build(),
        contentDescription = "Movie Poster",
        modifier = Modifier
            .padding(
                top = if (isFocused) 0.dp else 30.dp,
                bottom = bottomPadding
            )
            .clickable { onClick() }
            .graphicsLayer {
                rotationZ = rotationDegrees
                this.alpha = alpha
                scaleX = scale
                scaleY = scale
            }
            .size(200.dp, 300.dp)
            .shadow(
                5.dp,
                shape = shape
            )
            .clip(shape)
    )
}