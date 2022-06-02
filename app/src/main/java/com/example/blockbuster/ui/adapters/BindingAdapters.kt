package com.example.blockbuster


import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.blockbuster.domain.entities.Movie
import com.example.blockbuster.ui.adapters.MovieGridAdapter
import com.example.blockbuster.ui.viewmodel.ApiStatus


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Movie>?) {
    val adapter = recyclerView.adapter as MovieGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("apiStatus")
fun bindProgress(statusProgressBar: ProgressBar, status: ApiStatus?) {

    when (status) {
        ApiStatus.LOADING -> {
            statusProgressBar.visibility = View.VISIBLE
        }
        ApiStatus.DONE -> {
            statusProgressBar.visibility = View.GONE
        }
        ApiStatus.ERROR -> {
            statusProgressBar.visibility = View.GONE
        }
    }
}

@BindingAdapter("apiConnectionStatus")
fun bindConnection(statusText: TextView, status: ApiStatus?) {

    when (status) {
        ApiStatus.LOADING -> {
            statusText.visibility = View.GONE
        }
        ApiStatus.DONE -> {
            statusText.visibility = View.GONE
        }
        ApiStatus.ERROR -> {
            statusText.visibility = View.VISIBLE
        }
    }
}
