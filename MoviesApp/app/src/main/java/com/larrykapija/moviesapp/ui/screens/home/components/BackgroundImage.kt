package com.larrykapija.moviesapp.ui.screens.home.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import kotlinx.coroutines.delay

@Composable
fun BackgroundImage(imageUrl: String) {
    val currentImageUrl = remember { mutableStateOf(imageUrl) }
    val previousImageUrl = remember { mutableStateOf(imageUrl) }
    val opacity = remember { Animatable(0f) }

    val gradientColor = MaterialTheme.colorScheme.secondary

    LaunchedEffect(imageUrl) {
        if (imageUrl != currentImageUrl.value) {
            previousImageUrl.value = currentImageUrl.value
            currentImageUrl.value = imageUrl
            opacity.snapTo(0f)
            opacity.animateTo(1f, animationSpec = tween(durationMillis = 1000))
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = previousImageUrl.value,
            contentDescription = "old background image",
            modifier = Modifier
                .matchParentSize(),
            contentScale = ContentScale.Crop
        )

        // New image
        AsyncImage(
            model = currentImageUrl.value,
            contentDescription = "new background image",
            modifier = Modifier
                .matchParentSize()
                .graphicsLayer { alpha = opacity.value },
            contentScale = ContentScale.Crop
        )

        Canvas(modifier = Modifier.matchParentSize()) {
            val canvasHeight = size.height

            drawRect(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Black.copy(alpha = 0.3f),
                        Color.Transparent,
                        Color.Transparent,
                        gradientColor.copy(alpha = 0.5f),
                        gradientColor.copy(alpha = 0.7f),
                        gradientColor.copy(alpha = 0.9f),
                        gradientColor
                    )
                    ,
                    startY = canvasHeight * 0f,
                    endY = Float.POSITIVE_INFINITY,
                )
            )
        }
    }
}