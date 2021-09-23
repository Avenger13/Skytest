package com.test.skytest.screen.search

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.skytest.App
import com.test.skytest.data.network.api.search.response.Word
import com.test.skytest.databinding.ItemSearchListBinding


class WordAdapter(private val onWordClick: (Word) -> Unit) :
    PagingDataAdapter<Word, WordAdapter.WordVH>(WordDiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordVH {
        val inflater = LayoutInflater.from(parent.context)
        return WordVH(
            ItemSearchListBinding.inflate(inflater, parent, false),
            onWordClick
        )
    }

    override fun onBindViewHolder(holder: WordVH, position: Int) {
        holder.bind(getItem(position))
    }

    class WordVH(
        private val binding: ItemSearchListBinding,
        private val onWordClick: (Word) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(word: Word?) {
            if (word==null) {
                binding.text.text = "placeholder"
            }else {
                itemView.setOnClickListener { onWordClick(word) }
                binding.text.text = word.text
                binding.meanings.text = word.meanings.joinToString { it.translation.text }
                val imageUrl =
                    "https://" + word.meanings.firstOrNull()?.previewUrl?.takeIf { it.length > 2 }
                        ?.substring(2)
                App.appComponent.picasso.load(imageUrl)
                    .into(binding.image)
            }
        }
    }

    object WordDiffCallback : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Word, newItem: Word) = oldItem == newItem
    }
}