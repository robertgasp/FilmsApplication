package com.example.filmsapplication.model.repository

interface FilmsRepositoryInterface<T> {
    suspend fun getListOfFilmsFromInternetAsync(apiKey: String, language: String): T
}
