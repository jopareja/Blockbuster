package com.example.blockbuster


import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.blockbuster.network.Movie
import com.example.blockbuster.ui.main.MovieGridAdapter


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Movie>?) {
    val adapter = recyclerView.adapter as MovieGridAdapter
    adapter.submitList(data)
}
