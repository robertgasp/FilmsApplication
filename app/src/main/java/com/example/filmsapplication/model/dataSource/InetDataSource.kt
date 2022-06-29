package com.example.filmsapplication.model.dataSource

interface InetDataSource<T> {

    suspend fun getDataAsync(apiKey: String, language: String): T
}