package com.example.blockbuster.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.blockbuster.R
import com.example.blockbuster.databinding.MovieDetailFragmentBinding
import com.example.blockbuster.domain.entities.UserRatingRequest
import com.example.blockbuster.ui.viewmodel.MovieDetailViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.NonCancellable.cancel

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    companion object {
        const val BACKDROP = "backdrop"
        const val POSTER = "poster"
        const val TITLE = "title"
        const val DATE = "date"
        const val RATING = "rating"
        const val OVERVIEW = "overview"
    }

    private val viewModel: MovieDetailViewModel by viewModels()
    private var _binding: MovieDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var userInput: UserRatingRequest

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        _binding = DataBindingUtil.inflate(inflater,
            R.layout.movie_detail_fragment, container, false)

        drawMovieDetails()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rateButton.setOnClickListener { showRateDialog() }
    }

    private fun drawMovieDetails() {
        arguments?.let {

            it.getString(BACKDROP)?.let { backdrop ->
                Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w780$backdrop")
                    .transform(CenterCrop())
                    .placeholder(R.drawable.loading_animation).error(R.drawable.ic_broken_image)
                    .into(binding.movieBackdrop) }

            it.getString(POSTER)?.let { imgSrcUrl ->
                Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w185$imgSrcUrl")
                    .transform(CenterCrop())
                    .placeholder(R.drawable.loading_animation).error(R.drawable.ic_broken_image)
                    .into(binding.moviePoster)
            }

            binding.movieTitle.text = it.getString(TITLE)
            binding.movieDate.text = it.getString(DATE)
            binding.moviePopularity.text = getString(R.string.rating, String.format("%.2f", it.getFloat(
                RATING
            )))
            binding.movieOverview.text = it.getString(OVERVIEW)
        }
    }

    private fun showRateDialog() {
        context?.let {
            val singleItems = arrayOf("1 Star", "2 Stars", "3 Stars", "4 Stars", "5 Stars")
            MaterialAlertDialogBuilder(it)
                .setTitle(resources.getString(R.string.rate_title))
                .setNeutralButton(resources.getString(R.string.rate_cancel)) { dialog, _ ->
                    dialog.cancel()
                }
                .setPositiveButton(resources.getString(R.string.rate_ok)) { _, _ ->
                    viewModel.registerRating(userInput)
                }
                .setSingleChoiceItems(singleItems, -1) { _, which ->
                    userInput = UserRatingRequest(which)
                }
                .show()
        }
    }
}