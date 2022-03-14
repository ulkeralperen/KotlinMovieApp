package com.alperen.kotlinmovieretrofitnavigation.Model


data class getMovieInfoModel (
    val page: Long,
    val results: List<Result>,
    val totalPages: Long,
    val totalResults: Long
)

data class Result (
    val adult: Boolean,
    val backdropPath: String,
    val genreIDS: List<Long>,
    val id: String,
    val originalLanguage: OriginalLanguage,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Long
)

enum class OriginalLanguage {
    En,
    Hi,
    Ja
}
