package com.larrykapija.moviesapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.larrykapija.moviesapp.network.api.TmdbApiService
import com.larrykapija.moviesapp.network.response.MovieResponse
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val tmdbApiService: TmdbApiService
) : ViewModel() {

    private val _popularMovies = MutableLiveData<MovieResponse>()
    val popularMovies: LiveData<MovieResponse> = _popularMovies

    private val _nowPlayingMovies = MutableLiveData<MovieResponse>()
    val nowPlayingMovies: LiveData<MovieResponse> = _nowPlayingMovies

    private val _upcomingMovies = MutableLiveData<MovieResponse>()
    val upcomingMovies: LiveData<MovieResponse> = _upcomingMovies

    private val _topRatedMovies = MutableLiveData<MovieResponse>()
    val topRatedMovies: LiveData<MovieResponse> = _topRatedMovies

    init {
        fetchPopularMovies()
        fetchNowPlayingMovies()
        fetchUpcomingMovies()
        fetchTopRatedMovies()
    }

    private fun fetchPopularMovies() {
        viewModelScope.launch {
            try {
                val request = tmdbApiService.getPopularMovies()

                val response = request.awaitResponse()

                if (response.isSuccessful) {
                    _popularMovies.value = response.body()
                }
            } catch (_: Exception) { }
        }
    }

    private fun fetchNowPlayingMovies() {
        viewModelScope.launch {
            try {
                val request = tmdbApiService.getNowPlayingMovies()

                val response = request.awaitResponse()

                if (response.isSuccessful) {
                    _nowPlayingMovies.value = response.body()
                }
            } catch (_: Exception) { }
        }
    }

    private fun fetchUpcomingMovies() {
        viewModelScope.launch {
            try {
                val request = tmdbApiService.getUpcomingMovies()

                val response = request.awaitResponse()

                if (response.isSuccessful) {
                    _upcomingMovies.value = response.body()
                }
            } catch (_: Exception) { }
        }
    }

    private fun fetchTopRatedMovies() {
        viewModelScope.launch {
            try {
                val request = tmdbApiService.getTopRatedMovies()

                val response = request.awaitResponse()

                if (response.isSuccessful) {
                    _topRatedMovies.value = response.body()
                }
            } catch (_: Exception) { }
        }
    }
}