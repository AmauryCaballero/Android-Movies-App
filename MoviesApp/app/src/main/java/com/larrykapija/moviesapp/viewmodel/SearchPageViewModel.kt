package com.larrykapija.moviesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.larrykapija.moviesapp.network.api.TmdbApiService
import com.larrykapija.moviesapp.network.response.MovieResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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

    fun searchMovies(query: String) {
        if (query.isEmpty()) return

        viewModelScope.launch {
            _uiState.value = SearchState.Loading
            try {
                val response = tmdbApiService.getMoviesCollection(query = query)
                if (response.isSuccessful && response.body() != null) {
                    _uiState.value = SearchState.Success(response.body()!!)
                } else {
                    _uiState.value = SearchState.Error("No results found")
                }
            } catch (e: Exception) {
                _uiState.value = SearchState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

sealed class SearchState {
    object Empty : SearchState()
    object Loading : SearchState()
    data class Success(val movies: MovieResponse) : SearchState()
    data class Error(val message: String) : SearchState()
}