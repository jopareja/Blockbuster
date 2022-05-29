package com.example.blockbuster.ui.view

import android.os.Bundle
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
import com.example.blockbuster.ui.adapters.MovieGridAdapter
import com.example.blockbuster.ui.viewmodel.MainViewModel


class MainFragment : Fragment() {

    //Connect our UI Fragment with our UI Data Holder
    private val viewModel: MainViewModel by viewModels()

    private var  _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerViewLayoutMgr : GridLayoutManager

    private var popularMoviesPage = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        _binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        recyclerViewLayoutMgr = binding.photosGrid.layoutManager as GridLayoutManager

        initRecyclerView()
        recyclerViewOnScrollListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // this allows the bound layout access to all the data in the VieWModel
        binding.viewModel = viewModel
        // This is used so that the binding can observe LiveData updates
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun initRecyclerView() {
        binding.photosGrid.adapter = MovieGridAdapter()
    }

    private fun recyclerViewOnScrollListener() {
        binding.photosGrid.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val lastVisibleItem = recyclerViewLayoutMgr.findLastCompletelyVisibleItemPosition() + 1

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