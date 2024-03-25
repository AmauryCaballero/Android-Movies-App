package com.larrykapija.moviesapp.network.response

// Response model for popular, now playing, upcoming, and top rated movies
data class MovieResponse(
    val page: Int,
    val results: List<Movie>,
    val total_results: Int,
    val total_pages: Int
)

data class Movie(
    val id: Int,
)