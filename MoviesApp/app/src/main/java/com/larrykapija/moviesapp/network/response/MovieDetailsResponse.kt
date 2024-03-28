package com.larrykapija.moviesapp.network.response

import com.google.gson.annotations.SerializedName

// Movie Details model
data class MovieDetails(
    val id: Int,
    val title: String,
    val overview: String,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("release_date")
    val releaseDate: String?,

    val runtime: Int?, // Movie duration in minutes
    val genres: List<Genre>,
    val adult: Boolean,

    @SerializedName("genre_ids")
    val genreIds: List<Int>,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("original_title")
    val originalTitle: String,
    val popularity: Double,
    val video: Boolean,

    @SerializedName("vote_count")
    val voteCount: Int
)

// Genre model used in MovieDetails
data class Genre(
    val id: Int,
    val name: String
)