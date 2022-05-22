package com.example.blockbuster.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.blockbuster.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    //Connect our UI Fragment with our UI Data Holder
    private val viewModel: MainViewModel by viewModels()

    private lateinit var  binding: MainFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //This is where the UI will get automatically updated with LiveData
        viewModel.requestStatus.observe(viewLifecycleOwner)
        { currentStatus -> binding.popularMoviesTitle.text = currentStatus }
    }

}