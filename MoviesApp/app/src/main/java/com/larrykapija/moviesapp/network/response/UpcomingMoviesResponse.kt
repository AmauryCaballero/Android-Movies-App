package com.larrykapija.moviesapp.network.response

import com.larrykapija.moviesapp.models.Dates

data class UpcomingMoviesResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)
