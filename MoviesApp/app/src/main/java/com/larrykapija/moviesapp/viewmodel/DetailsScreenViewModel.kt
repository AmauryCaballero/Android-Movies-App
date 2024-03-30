package com.larrykapija.moviesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.larrykapija.moviesapp.network.response.Movie
import com.larrykapija.moviesapp.network.response.toMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor() : ViewModel() {
    private val _movie = MutableLiveData<Movie?>()
    val movie: LiveData<Movie?> = _movie

    fun setMovieFromJson(movieJson: String) {
        _movie.value = movieJson.toMovie()
    }
}