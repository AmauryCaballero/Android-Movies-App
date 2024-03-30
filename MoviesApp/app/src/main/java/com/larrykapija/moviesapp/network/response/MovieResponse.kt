package com.larrykapija.moviesapp.network.response

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.google.gson.annotations.SerializedName
import com.larrykapija.moviesapp.models.Dates

// Response model for popular, now playing, upcoming, and top rated movies
open class MovieResponse(
    val dates: Dates?,
    val page: Int?,
    val results: List<Movie>,

    @SerializedName("total_results")
    val totalResults: Int?,

    @SerializedName("total_pages")
    val totalPages: Int?
)

// Movie model used in the MovieResponse and for details
data class Movie(
    val id: Int,

    @SerializedName("name", alternate = ["original_name", "title"])
    val title: String?,

    val overview: String?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @SerializedName("vote_average")
    val voteAverage: Double?,

    @SerializedName("release_date")
    val releaseDate: String?
)

fun Movie.toJson(): String {
    val gson = GsonBuilder().serializeNulls().create()
    return gson.toJson(this)
}

fun String.toMovie(): Movie? {
    return try {
        Gson().fromJson(this, Movie::class.java)
    } catch (e: JsonSyntaxException) {
        null
    }
}