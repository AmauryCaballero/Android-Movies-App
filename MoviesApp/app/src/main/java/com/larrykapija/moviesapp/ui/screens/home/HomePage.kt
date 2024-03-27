package com.larrykapija.moviesapp.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.larrykapija.moviesapp.ui.viewmodel.HomePageViewModel
import androidx.compose.runtime.livedata.observeAsState
import com.larrykapija.moviesapp.ui.screens.components.VerticalSpacer
import com.larrykapija.moviesapp.ui.screens.home.components.BackgroundImage
import com.larrykapija.moviesapp.ui.screens.home.components.MovieItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomePage(
    innerPadding: PaddingValues,
    viewModel: HomePageViewModel = viewModel(LocalContext.current as ViewModelStoreOwner)
) {
    val movies = viewModel.popularMovies.observeAsState().value?.results ?: listOf()
    val pagerState = rememberPagerState(pageCount = {
        movies.size
    })

    Box(
        modifier = Modifier.padding(innerPadding),
        contentAlignment = Alignment.Center
    ) {

        movies.getOrNull(pagerState.currentPage)?.backdrop_path?.let { backdropPath ->
            val imageUrl = "https://image.tmdb.org/t/p/original$backdropPath"
            BackgroundImage(imageUrl)
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {

            // This space allow us to see the background image
            item {
                VerticalSpacer(
                    35
                )
            }

            item {
                HorizontalPager(
                    state = pagerState,
                    pageSize = PageSize.Fixed(200.dp),
                    contentPadding = PaddingValues(start = 100.dp)
                ) { page ->
                    MovieItem(
                        movie = movies[page],
                        index = page,
                        focusedItemIndex = pagerState.currentPage
                    )
                }
            }
        }
    }
}
