package com.larrykapija.moviesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.larrykapija.moviesapp.network.api.TmdbApiService
import com.larrykapija.moviesapp.network.response.MovieDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchPageViewModel @Inject constructor(
    private val tmdbApiService: TmdbApiService
) : ViewModel() {

    private val _uiState = MutableLiveData<SearchState>()
    val uiState: LiveData<SearchState> = _uiState

    init {
        _uiState.value = SearchState.Empty
    }

}

sealed class SearchState {
    object Empty : SearchState()
    object Loading : SearchState()
    data class Success(val movies: MovieDetails) : SearchState()
    data class Error(val message: String) : SearchState()
}