package com.larrykapija.moviesapp.network.api

import com.larrykapija.moviesapp.network.response.NowPlayingResponse
import com.larrykapija.moviesapp.network.response.PopularMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface TmdbApiService {
    @GET("movie/popular")
    fun getPopularMovies(
        @Header("Authorization") accessToken: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Call<PopularMoviesResponse>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Header("Authorization") accessToken: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Call<NowPlayingResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Header("Authorization") accessToken: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Call<UpcomingMoviesResponse>
}
