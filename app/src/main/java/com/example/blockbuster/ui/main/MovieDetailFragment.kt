package com.example.blockbuster.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.blockbuster.R
import com.example.blockbuster.databinding.MovieDetailFragmentBinding


class MovieDetailFragment : Fragment() {

    private var _binding: MovieDetailFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        _binding = DataBindingUtil.inflate(inflater,
            R.layout.movie_detail_fragment, container, false)

        return binding.root
    }
}