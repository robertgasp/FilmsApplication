package com.example.filmsapplication.di

import com.example.filmsapplication.model.dataSource.RetrofitImpl
import com.example.filmsapplication.model.repository.FilmObject
import com.example.filmsapplication.model.repository.FilmsList
import com.example.filmsapplication.model.repository.FilmsRepository
import com.example.filmsapplication.model.repository.FilmsRepositoryInterface
import com.example.filmsapplication.ui.viewModel.FilmsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val application = module {
    single<FilmsRepositoryInterface<FilmsList>> { FilmsRepository(RetrofitImpl()) }
}

val filmsModule = module {
    viewModel { FilmsViewModel(get()) }
}