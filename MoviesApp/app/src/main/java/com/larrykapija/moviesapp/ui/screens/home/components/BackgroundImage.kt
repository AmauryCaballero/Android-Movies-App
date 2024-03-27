package com.larrykapija.moviesapp.ui.screens.home.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun BackgroundImage(imageUrl: String) {

    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "home page background image",
            modifier = Modifier
                .matchParentSize(),
            contentScale = ContentScale.Crop
        )

        Canvas(modifier = Modifier.matchParentSize()) {
            val canvasHeight = size.height
            val gradientColor = Color.Black

            drawRect(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        gradientColor.copy(alpha = 0.3f),
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