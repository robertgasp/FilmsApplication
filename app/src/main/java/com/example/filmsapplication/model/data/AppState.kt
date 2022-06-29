package com.example.filmsapplication.model.data

import com.example.filmsapplication.model.repository.FilmObject

sealed class AppState {
    data class Success(val filmsData: List<FilmObject>) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading (val progress:Int?): AppState()
}
