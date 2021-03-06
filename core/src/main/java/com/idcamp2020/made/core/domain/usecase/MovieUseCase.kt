package com.idcamp2020.made.core.domain.usecase

import com.idcamp2020.made.core.data.Resource
import com.idcamp2020.made.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getMovie(query: String): Flow<Resource<List<Movie>>>

    fun getMovieFavorite(query: String): Flow<List<Movie>>

    fun getMovieSearch(query: String): Flow<List<Movie>>

    fun setMovieFavorite(movie: Movie, state: Boolean)
}