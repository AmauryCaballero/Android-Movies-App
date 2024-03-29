package com.larrykapija.moviesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.larrykapija.moviesapp.network.api.TmdbApiService
import com.larrykapija.moviesapp.network.response.MovieResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val tmdbApiService: TmdbApiService
) : ViewModel() {

    // Define MutableStateFlows for each category of movies. Initialized with empty lists.
    private val _popularMovies = MutableStateFlow<MovieResponse?>(null)
    val popularMovies: StateFlow<MovieResponse?> = _popularMovies

    private val _nowPlayingMovies = MutableStateFlow<MovieResponse?>(null)
    val nowPlayingMovies: StateFlow<MovieResponse?> = _nowPlayingMovies

    private val _upcomingMovies = MutableStateFlow<MovieResponse?>(null)
    val upcomingMovies: StateFlow<MovieResponse?> = _upcomingMovies

    private val _topRatedMovies = MutableStateFlow<MovieResponse?>(null)
    val topRatedMovies: StateFlow<MovieResponse?> = _topRatedMovies

    init {
        // Fetch movies for each category using the generic fetchMovies function
        fetchMovies({ tmdbApiService.getPopularMovies() }, _popularMovies)
        fetchMovies({ tmdbApiService.getNowPlayingMovies() }, _nowPlayingMovies)
        fetchMovies({ tmdbApiService.getUpcomingMovies() }, _upcomingMovies)
        fetchMovies({ tmdbApiService.getTopRatedMovies() }, _topRatedMovies)
    }

    /**
     * Generic function to fetch movies using a given API call function and update the corresponding StateFlow.
     * @param fetchFunction The suspend function to execute for fetching movies from the API.
     * @param movieStateFlow The MutableStateFlow to be updated with the fetched movies.
     */
    private fun fetchMovies(fetchFunction: suspend () -> Response<MovieResponse>, movieStateFlow: MutableStateFlow<MovieResponse?>) {
        viewModelScope.launch {
            try {
                val response = fetchFunction()
                if (response.isSuccessful) {

                    movieStateFlow.value = response.body()
                } else {
                    // TODO: Handle the case of an unsuccessful response, e.g., by logging or showing an error message.
                }
            } catch (e: Exception) {
                // TODO: Handle any exceptions that might occur during the network call or response handling.
            }
        }
    }
}