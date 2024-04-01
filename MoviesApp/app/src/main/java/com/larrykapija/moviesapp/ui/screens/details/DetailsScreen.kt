package com.larrykapija.moviesapp.ui.screens.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.larrykapija.moviesapp.R
import com.larrykapija.moviesapp.ui.screens.components.InfiniteAnimation
import com.larrykapija.moviesapp.ui.screens.details.components.MovieDetailsLabel
import com.larrykapija.moviesapp.ui.screens.details.components.YouTubeThumbnail
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
    val videos = viewModel.movieVideos.collectAsState().value

    val context = LocalContext.current

    val openYouTubeVideo: (String) -> Unit = { videoKey ->
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$videoKey"))
        val url = "http://www.youtube.com/watch?v=$videoKey"
        val webIntent = Intent(Intent.ACTION_VIEW,
            Uri.parse(url))
        try {
            context.startActivity(intent.resolveActivity(context.packageManager)?.let { intent } ?: webIntent)
        } catch (e: Exception) {
            context.startActivity(webIntent)
        }
    }

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
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(space.dp)
                    .aspectRatio(1.5f)
                    .blur(5.dp)
            )

            Column(
                Modifier
                    .padding(16.dp)
            ) {

                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {

                    AsyncImage(
                        model =  ImageRequest.Builder(LocalContext.current)
                            .data("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                            .crossfade(true)
                            .build(),
                        contentScale = ContentScale.FillWidth,
                        contentDescription = null,
                        modifier = Modifier
                            .offset(y = (-80).dp)
                            .size(120.dp)
                            .padding(end = 5.dp)
                            .aspectRatio(0.7f)
                            .shadow(10.dp, shape = RoundedCornerShape(size = 10.dp))
                            .clip(RoundedCornerShape(size = 10.dp))
                    )

                    Text(
                        modifier = Modifier
                            .offset(y = (-20).dp)
                            .align(Alignment.Bottom),
                        text = "${movie.title} ",
                        overflow = TextOverflow.Visible,
                        style = MaterialTheme.typography.titleLarge
                            .merge(MaterialTheme.colorScheme.onPrimary),
                        textAlign = TextAlign.Center
                    )
                }

                Text(
                    modifier = Modifier
                        .padding(bottom = 6.dp),
                    text = movie.releaseDate ?: "",
                    style = MaterialTheme.typography.labelSmall.merge(MaterialTheme.colorScheme.onPrimary),
                    textAlign = TextAlign.Center
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                )

                MovieDetailsLabel(details)

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    modifier = Modifier
                        .align(Alignment.End),
                    text = movie.overview ?: "",
                    style = MaterialTheme.typography.bodySmall.merge(MaterialTheme.colorScheme.onPrimary)
                )

                if (videos?.isNotEmpty() == true) {
                    LazyRow(
                        Modifier.padding(top = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val youtubeVideos = videos.filter { video -> video.site?.equals("YouTube") == true }

                        items(youtubeVideos) { video ->
                            Card(
                                modifier = Modifier
                                    .height(150.dp)
                                    .aspectRatio(1.5f)
                            ) {
                                YouTubeThumbnail(
                                    video,
                                    onThumbnailClick = {
                                        video.key?.let { openYouTubeVideo(it) }
                                    }
                                )
                            }
                        }
                    }
                }
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