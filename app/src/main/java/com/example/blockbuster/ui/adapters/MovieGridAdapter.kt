package com.example.blockbuster.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.blockbuster.R
import com.example.blockbuster.databinding.GridViewItemBinding
import com.example.blockbuster.domain.entities.Movie
import com.example.blockbuster.ui.view.MainFragmentDirections


class MovieGridAdapter : ListAdapter<Movie, MovieGridAdapter.MovieViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
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
            binding.cardView.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToMovieDetailFragment(
                    backdrop = movie.backdrop, poster = movie.imgSrcUrl, title = movie.title,
                    date = movie.releaseDate, rating = movie.rating, overview = movie.overview,
                    movieid = movie.id.toInt()
                )
                binding.cardView.findNavController().navigate(action)
            }
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