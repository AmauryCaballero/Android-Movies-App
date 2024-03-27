package com.larrykapija.moviesapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.larrykapija.moviesapp.BuildConfig
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

    val greetings = "Hello"
}