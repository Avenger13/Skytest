package com.test.skytest.screen.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.skytest.appComponent
import com.test.skytest.data.network.api.search.response.Word
import com.test.skytest.databinding.ItemLoadStateBinding
import com.test.skytest.databinding.ItemSearchListBinding


class WordAdapter(private val onWordClick: (Word) -> Unit) :
    PagingDataAdapter<Word, RecyclerView.ViewHolder>(WordDiffCallback) {

    private companion object {
        const val TYPE_WORD = 0
        const val TYPE_PROGRESS = 1
        val PROGRESS_WORD = Word(-1, "", emptyList())
    }


    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            PROGRESS_WORD -> TYPE_PROGRESS
            else -> TYPE_WORD
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            TYPE_WORD -> {
                WordVH(
                    ItemSearchListBinding.inflate(inflater, parent, false),
                    onWordClick
                )
            }
            else -> {
                ProgressVH(ItemLoadStateBinding.inflate(inflater, parent, false))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is WordVH -> {
                getItem(position)?.let { holder.bind(it) }
            }
        }

    }

    fun showProgress(show: Boolean) {
//        when (show) {
//            true -> {
//                    submitList(currentList+PROGRESS_WORD)
//            }
//
//            false -> {
//                if (currentList.isNotEmpty() && currentList.last() == PROGRESS_WORD) {
//                    submitList(currentList.dropLast(1))
//                }
//            }
//        }
    }

    class WordVH(
        private val binding: ItemSearchListBinding,
        private val onWordClick: (Word) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(word: Word) {
            itemView.setOnClickListener { onWordClick(word) }
            binding.text.text = word.text
            binding.meanings.text = word.meanings.joinToString { it.translation.text }
            val imageUrl =
                "https://" + word.meanings.firstOrNull()?.previewUrl?.takeIf { it.length > 2 }
                    ?.substring(2)
            itemView.context.appComponent.picasso.load(imageUrl)
                .into(binding.image)

        }
    }

    class ProgressVH(binding: ItemLoadStateBinding) : RecyclerView.ViewHolder(binding.root)

    object WordDiffCallback : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Word, newItem: Word) = oldItem == newItem
    }
}