package com.example.filmsapplication.model.repository

import com.google.gson.annotations.SerializedName

class FilmsList {
    @SerializedName("results")
    private var films: List<FilmObject>? = null

    fun getFilmsList(): List<FilmObject> {
        return films!!
    }
}