package com.larrykapija.moviesapp.ui.screens.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.larrykapija.moviesapp.R
import com.larrykapija.moviesapp.network.response.Video

@Composable
fun YouTubeThumbnail(video: Video, onThumbnailClick: () -> Unit) {
    video.key?.let { videoKey ->
        val thumbnailUrl = "https://img.youtube.com/vi/$videoKey/maxresdefault.jpg"

        Box {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(thumbnailUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Video Thumbnail",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onThumbnailClick)
                    .blur(1.dp),
                contentScale = ContentScale.Crop
            )

            Image(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "play icon",
                modifier = Modifier
                    .shadow(10.dp)
                    .size(50.dp)
                    .align(Alignment.Center),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
        }
    }
}