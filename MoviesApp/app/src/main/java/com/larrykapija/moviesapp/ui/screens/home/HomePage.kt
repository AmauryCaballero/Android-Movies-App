package com.larrykapija.moviesapp.ui.screens.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.larrykapija.moviesapp.ui.viewmodel.HomePageViewModel
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.larrykapija.moviesapp.ui.screens.components.VerticalSpacer
import com.larrykapija.moviesapp.ui.screens.home.components.BackgroundImage
import com.larrykapija.moviesapp.ui.screens.home.components.MovieItem
import com.larrykapija.moviesapp.ui.screens.home.components.MoviesGrid

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomePage(
    innerPadding: PaddingValues,
    viewModel: HomePageViewModel = viewModel(LocalContext.current as ViewModelStoreOwner)
) {
    val popularMovies = viewModel.popularMovies.collectAsState().value?.results ?: listOf()
    val nowPlayingMovies = viewModel.nowPlayingMovies.collectAsState().value?.results ?: listOf()
    val upcomingMovies = viewModel.upcomingMovies.collectAsState().value?.results ?: listOf()
    val topRatedMovies = viewModel.topRatedMovies.collectAsState().value?.results ?: listOf()

    val pagerState = rememberPagerState(pageCount = {
        popularMovies.size
    })

    Box(
        modifier = Modifier.padding(innerPadding),
        contentAlignment = Alignment.Center
    ) {

        popularMovies.getOrNull(pagerState.currentPage)?.backdrop_path?.let { backdropPath ->
            val imageUrl = "https://image.tmdb.org/t/p/original$backdropPath"
            BackgroundImage(imageUrl)
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {

            // This space allow us to see the background image
            item {
                VerticalSpacer(
                    33
                )
            }
            
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = "Popular",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
            }

            item {
                HorizontalPager(
                    state = pagerState,
                    pageSize = PageSize.Fixed(200.dp),
                    contentPadding = PaddingValues(start = 100.dp)
                ) { page ->
                    MovieItem(
                        movie = popularMovies[page],
                        index = page,
                        focusedItemIndex = pagerState.currentPage
                    )
                }
            }

            item {
                Box {
                    GradientBackground(modifier = Modifier.matchParentSize())

                    Column {

                        MoviesGrid(title = "Playing now", moviesList = nowPlayingMovies)

                        MoviesGrid(title = "Top rated", moviesList = topRatedMovies)

                        MoviesGrid(title = "Upcoming", moviesList = upcomingMovies)
                    }
                }
            }

        }
    }
}

@Composable
private fun GradientBackground(modifier: Modifier) {

    val gradientColor = MaterialTheme.colorScheme.secondary

    Canvas(modifier = modifier) {
        val canvasHeight = size.height
        drawRect(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color.Transparent,
                    gradientColor.copy(alpha = 0.9f),
                    gradientColor,
                    gradientColor,
                    gradientColor,
                    gradientColor
                ),
                startY = canvasHeight * 0f,
                endY = Float.POSITIVE_INFINITY,
            )
        )
    }

}