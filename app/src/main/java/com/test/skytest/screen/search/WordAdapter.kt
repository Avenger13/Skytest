package com.test.skytest.screen.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.test.skytest.data.network.api.search.response.Word
import com.test.skytest.databinding.ItemSearchListBinding


class WordAdapter(private val picasso: Picasso, private val onWordClick: (Word) -> Unit) :
    RecyclerView.Adapter<WordAdapter.WordVH>() {

    private val words: MutableList<Word> = mutableListOf()

    fun setWords(data: List<Word>) {
        words.clear()
        words.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return words.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordVH {
        return WordVH(
            ItemSearchListBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            picasso,
            onWordClick
        )
    }

    override fun onBindViewHolder(holder: WordVH, position: Int) {
        holder.bind(words[position])
    }


    class WordVH(
        private val binding: ItemSearchListBinding,
        private val picasso: Picasso,
        private val onWordClick: (Word) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(word: Word) {
            itemView.setOnClickListener { onWordClick(word) }
            binding.text.text = word.text
            binding.meanings.text = word.meanings.joinToString { it.translation.text }
            val imageUrl =
                "https://"+word.meanings.firstOrNull()?.previewUrl?.takeIf { it.length > 2 }?.substring(2)
            picasso.load(imageUrl)
                .into(binding.image)

        }
    }
}