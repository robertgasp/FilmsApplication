package com.example.filmsapplication.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.filmsapplication.BuildConfig
import com.example.filmsapplication.R
import com.example.filmsapplication.databinding.FragmentDetailsPageBinding
import com.example.filmsapplication.model.repository.FilmObject
import com.squareup.picasso.Picasso

class DetailsPageFragment : Fragment() {

    private var _binding: FragmentDetailsPageBinding? = null
    private val binding get() = _binding!!

    private lateinit var movie: FilmObject
    private val imageSourcePath: String = BuildConfig.IMAGE_SOURCE_PATH


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            movie = it.getParcelable(MOVIE)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        Picasso
            .get()
            .load(imageSourcePath + movie.posterPath)
            .resizeDimen(R.dimen.image_description_size, R.dimen.image_description_size)
            .centerInside()
            .into(cover)

        title.text = movie.title
        year.text = movie.releaseDate

        if (movie.mediaType == "movie") {
            mediaType.text = "Фильм"
        } else mediaType.text = movie.mediaType

        voteAverage.text = "Рейтинг: ${movie.voteAverage}"
        description.text = movie.overview
    }

    companion object {
        val MOVIE = "movie"
    }
}