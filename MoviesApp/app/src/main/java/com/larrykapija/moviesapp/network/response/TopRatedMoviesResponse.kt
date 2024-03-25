package com.larrykapija.moviesapp.network.response

data class TopRatedMoviesResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)
