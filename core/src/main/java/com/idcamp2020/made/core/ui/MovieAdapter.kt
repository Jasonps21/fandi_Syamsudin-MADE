package com.idcamp2020.made.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.idcamp2020.made.core.BuildConfig
import com.idcamp2020.made.core.databinding.ItemMovieBinding
import com.idcamp2020.made.core.domain.model.Movie
import com.idcamp2020.made.core.utils.DiffUtils

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?){
        if (newListData.isNullOrEmpty()) return
        val diffUtilsCallback = DiffUtils(listData, newListData)
        val diffUtilsResult = DiffUtil.calculateDiff(diffUtilsCallback)

        with(listData){
            clear()
            addAll(newListData)
        }

        diffUtilsResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val itemMovieBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding){
                tvTitle.text = movie.title
                tvRelease.text = movie.releaseDate
                tvVote.text = movie.voteAverage.toString()
                Glide.with(itemView.context)
                    .load(BuildConfig.IMAGE_URL + movie.posterPath)
                    .dontTransform()
                    .into(ivPoster)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}