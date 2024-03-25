package com.larrykapija.moviesapp.network.api

import com.larrykapija.moviesapp.network.response.PopularMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApiService {
    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Call<PopularMoviesResponse>
}
