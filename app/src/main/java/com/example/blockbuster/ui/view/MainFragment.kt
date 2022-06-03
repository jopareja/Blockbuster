package com.example.blockbuster.ui.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.blockbuster.R
import com.example.blockbuster.databinding.MainFragmentBinding
import com.example.blockbuster.ui.adapters.MovieGridAdapter
import com.example.blockbuster.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    //Connect our UI Fragment with our UI Data Holder
    private val viewModel: MainViewModel by viewModels()

    private var  _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerViewLayoutMgr : GridLayoutManager
    /**private var popularMoviesPage = 1 */


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        _binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        recyclerViewLayoutMgr = binding.photosGrid.layoutManager as GridLayoutManager

        initRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // this allows the bound layout access to all the data in the VieWModel
        binding.viewModel = viewModel
        // This is used so that the binding can observe LiveData updates
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun onStart() {
        super.onStart()
        viewModel.updateMovies()
        /**recyclerViewOnScrollListener()*/
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        val item = menu.findItem(R.id.app_bar_search)
        val searchIcon = item.actionView as SearchView
        searchIcon.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {

                if (!newText.isNullOrEmpty()) {
                    viewModel.filterMovies(newText)
                } else viewModel.updateMovies()
                return false
            }
        })
    }

    private fun initRecyclerView() {
        binding.photosGrid.adapter = MovieGridAdapter()
    }

    /**
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
    */
}