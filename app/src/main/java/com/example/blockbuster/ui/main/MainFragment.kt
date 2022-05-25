package com.example.blockbuster.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blockbuster.R
import com.example.blockbuster.databinding.MainFragmentBinding

const val TAG = "MainFragment"
class MainFragment : Fragment() {

    //Connect our UI Fragment with our UI Data Holder
    private val viewModel: MainViewModel by viewModels()

    private lateinit var  binding: MainFragmentBinding

    private lateinit var popularMoviesLayoutMgr : GridLayoutManager

    private var popularMoviesPage = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        popularMoviesLayoutMgr = binding.photosGrid.layoutManager as GridLayoutManager
        binding.photosGrid.adapter = MovieGridAdapter()
        fetchOnScrollListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // this allows the bound layout access to all the data in the VieWModel
        binding.viewModel = viewModel

        // This is used so that the binding can observe LiveData updates
        binding.lifecycleOwner = viewLifecycleOwner

        // Setup a click listener for movies pictures.
    }

    private fun fetchOnScrollListener() {
        binding.photosGrid.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val lastVisibleItem = popularMoviesLayoutMgr.findLastCompletelyVisibleItemPosition() + 1
                //Log.d(TAG, "LastItemV ${popularMoviesLayoutMgr.findLastCompletelyVisibleItemPosition() + 1}")

                if (lastVisibleItem >= 19) {
                    binding.photosGrid.removeOnScrollListener(this)
                    popularMoviesPage++
                    viewModel.updateMovies(popularMoviesPage)
                    binding.photosGrid.addOnScrollListener(this)
                }
            }
        })
    }
}