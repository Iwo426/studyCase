package com.itunes.searchapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.itunes.searchapi.R
import com.itunes.searchapi.databinding.SearchItemLayoutBinding
import com.itunes.searchapi.response.Result
import com.itunes.searchapi.utils.dateFormat
import com.itunes.searchapi.utils.loadImage

class SearchAdapter(val context : Context?) :
    PagingDataAdapter<Result, SearchAdapter.MovieViewHolder>(SearchComparator) {

    private var listener: ItemInterface? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        return MovieViewHolder(
            SearchItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bindResults(it) }

    }

    inner class MovieViewHolder(private val binding: SearchItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindResults(item: Result) = with(binding) {

            tvCollectionName.text = item.collectionName ?: context?.getString(R.string.name_not_found)
            tvCollectionPrice.text = (item.collectionPrice ?:  context?.getString(R.string.cost_not_found)).toString()
            tvDate.text = dateFormat(item.releaseDate.toString())
            item.artworkUrl100?.let { imgArtWork.loadImage(it) }
            itemView.setOnClickListener {
                listener?.sendData(item)
            }
        }
    }

    object SearchComparator : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.trackId == newItem.trackId
        }

        override fun areContentsTheSame(
            oldItem: Result,
            newItem: Result
        ): Boolean {
            return oldItem == newItem
        }
    }

    interface ItemInterface {
        fun sendData(data: Result)
    }

    fun interfaceListener(listener: ItemInterface?) {
        this.listener = listener
    }
}