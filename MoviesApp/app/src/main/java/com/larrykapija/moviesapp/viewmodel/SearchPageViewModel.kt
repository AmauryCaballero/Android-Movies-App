package com.larrykapija.moviesapp.viewmodel

import androidx.lifecycle.ViewModel
import com.larrykapija.moviesapp.network.api.TmdbApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchPageViewModel @Inject constructor(
    private val tmdbApiService: TmdbApiService
) : ViewModel() {

}