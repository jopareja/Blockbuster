package com.example.blockbuster.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.blockbuster.R
import com.example.blockbuster.databinding.GridViewItemBinding
import com.example.blockbuster.network.Movie


class MovieGridAdapter : ListAdapter<Movie, MovieGridAdapter.MovieViewHolder>(DiffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : MovieGridAdapter.MovieViewHolder {
        return MovieViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MovieGridAdapter.MovieViewHolder, position: Int) {
        val moviePoster = getItem(position)
        holder.bind(moviePoster)
    }

    class MovieViewHolder(private var binding: GridViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movieName.text = movie.title
            Glide.with(itemView).load("https://image.tmdb.org/t/p/w185${movie.imgSrcUrl}")
                .transform(CenterCrop())
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
                .into(binding.movieImage)
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }

    }
}
//.load("https://image.tmdb.org/t/p/w185${movie.posterPath}")