package com.example.filmsapplication.model.dataSource

import com.example.filmsapplication.model.repository.FilmObject
import com.example.filmsapplication.model.repository.FilmsList
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("trending/movie/day")
    fun getListOfFilmsAsync(
        @Query("api_key") apiKey: String,
        @Query("language")language: String
    ): Deferred<FilmsList>
}