package com.example.filmsapplication.model.repository

import com.example.filmsapplication.model.dataSource.InetDataSource

class FilmsRepository(private val dataSource: InetDataSource<FilmsList>) :
    FilmsRepositoryInterface<FilmsList> {

    override suspend fun getListOfFilmsFromInternetAsync(
        apiKey: String,
        language: String
    ): FilmsList {
        return dataSource.getDataAsync(apiKey, language)
    }
}