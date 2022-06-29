package com.example.filmsapplication.ui.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsapplication.BuildConfig
import com.example.filmsapplication.databinding.FragmentActualBinding
import com.example.filmsapplication.model.data.AppState
import com.example.filmsapplication.model.repository.FilmObject
import com.example.filmsapplication.ui.recyclerViewAdapters.ActualFilmsAdapter
import com.example.filmsapplication.ui.viewModel.FilmsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ActualFragment : Fragment() {

    private var _binding: FragmentActualBinding? = null
    private val binding get() = _binding!!

    private val filmsViewModel: FilmsViewModel by viewModel()
    private var recyclerView: RecyclerView? = null
    private var adapter: ActualFilmsAdapter? = null
    private val apiKey: String = BuildConfig.API_KEY


    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentActualBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerViewActualFragment
        return binding.root
    }


    override fun onDetach() {
        adapter?.removeListener()
        super.onDetach()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()


        filmsViewModel.getMyLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }

        filmsViewModel.getFilms(apiKey, "ru-RU")
    }

    private fun initRecyclerView() {
        val lm = GridLayoutManager(context, 3)
        recyclerView?.layoutManager = lm
        adapter = ActualFilmsAdapter()
        recyclerView?.adapter = adapter
        adapter?.filmClickListener = ActualFilmsAdapter.FilmClickListener { movie ->
            filmClicked(movie)
        }
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {
                loadingLayout.visibility = View.GONE
                adapter?.setFilm(appState.filmsData)
            }
            is AppState.Loading -> {
                loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                loadingLayout.visibility = View.GONE
            }
            else -> {}
        }
    }

    private fun filmClicked(film: FilmObject) {
        val action =
            ActualFragmentDirections.actionActualFragmentToDetailsPageFragment(movie = film)
        view?.findNavController()?.navigate(action)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}