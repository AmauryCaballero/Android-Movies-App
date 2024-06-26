package com.larrykapija.moviesapp.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.larrykapija.moviesapp.network.api.TmdbApiService
import com.larrykapija.moviesapp.network.response.Movie
import com.larrykapija.moviesapp.network.response.MovieDetails
import com.larrykapija.moviesapp.network.response.Video
import com.larrykapija.moviesapp.network.response.toMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val tmdbApiService: TmdbApiService
) : ViewModel() {
    private val _movie = MutableStateFlow<Movie?>(null)
    val movie: StateFlow<Movie?> = _movie

    private val _movieDetails = MutableStateFlow<MovieDetails?>(null)
    val movieDetails: StateFlow<MovieDetails?> = _movieDetails

    private val _movieVideos = MutableStateFlow<List<Video>?>(null)
    val movieVideos: StateFlow<List<Video>?> = _movieVideos

    private val _openVideoEvent = MutableLiveData<String?>()
    val openVideoEvent: LiveData<String?>
        get() = _openVideoEvent

    fun onThumbnailClicked(videoKey: String?) {
        _openVideoEvent.value = videoKey
    }

    fun setMovieFromJson(movieJson: String) {
        val movie = movieJson.toMovie()
        _movie.value = movie

        movie?.let {
            fetchMovieDetails(it.id)
            fetchMovieVideos(it.id)
        }
    }

    private fun fetchMovieVideos(movieId: Int) {
        viewModelScope.launch {
            try {
                val response = tmdbApiService.getMovieVideos(movieId)
                if (response.isSuccessful) {
                    val videos = response.body()?.results
                    if (videos?.isNotEmpty() == true) {
                        _movieVideos.value = videos
                    }
                }
            } catch (_: Exception) {}
        }
    }

    private fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            try {
                val response = tmdbApiService.getMovieDetails(movieId)
                if (response.isSuccessful) {
                    _movieDetails.value = response.body()
                }
            } catch (_: Exception) {}
        }
    }
}