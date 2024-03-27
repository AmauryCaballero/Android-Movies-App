package com.larrykapija.moviesapp.network.api

import com.larrykapija.moviesapp.network.response.MovieDetails
import com.larrykapija.moviesapp.network.response.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApiService {

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "en-US"
    ): Call<MovieDetails>

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Call<MovieResponse>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Call<MovieResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Call<MovieResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Call<MovieResponse>
}
