package com.larrykapija.moviesapp.network.response

// Movie Details model
data class MovieDetails(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val backdrop_path: String?,
    val vote_average: Double,
    val release_date: String?,
    val runtime: Int?, // Movie duration in minutes
    val genres: List<Genre>,
)

// Genre model used in MovieDetails
data class Genre(
    val id: Int,
    val name: String
)