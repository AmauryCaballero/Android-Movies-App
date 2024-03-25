package com.larrykapija.moviesapp.network.response

import com.larrykapija.moviesapp.models.Dates

// Response model for popular, now playing, upcoming, and top rated movies
open class MovieResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Movie>,
    val total_results: Int,
    val total_pages: Int
)

// Movie model used in the MovieResponse and for details
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val backdrop_path: String?,
    val vote_average: Double,
    val release_date: String?
)