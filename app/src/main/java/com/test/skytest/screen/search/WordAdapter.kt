package com.test.skytest.screen.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.test.skytest.data.network.api.search.response.Word
import com.test.skytest.databinding.ItemProgressListBinding
import com.test.skytest.databinding.ItemSearchListBinding


class WordAdapter(private val picasso: Picasso, private val onWordClick: (Word) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val words: MutableList<Word> = mutableListOf()

    private companion object {
        const val TYPE_WORD = 0
        const val TYPE_PROGRESS = 1
        val PROGRESS_WORD = Word(-1, "", emptyList())
    }

    fun setWords(data: List<Word>) {
        words.clear()
        words.addAll(data)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return words.size
    }


    override fun getItemViewType(position: Int): Int {
        return when (words[position]) {
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
                    picasso,
                    onWordClick
                )
            }
            else -> {
                ProgressVH(ItemProgressListBinding.inflate(inflater, parent, false))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is WordVH -> {
                holder.bind(words[position])
            }
        }

    }

    fun showProgress(show: Boolean) {
        when (show) {
            true -> {
                words.add(PROGRESS_WORD)
                notifyItemInserted(words.size - 1)
            }

            false -> {
                if (words.isNotEmpty()) {
                    words.removeLast()
                    notifyItemRemoved(words.size - 1)
                }
            }
        }
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
                "https://" + word.meanings.firstOrNull()?.previewUrl?.takeIf { it.length > 2 }
                    ?.substring(2)
            picasso.load(imageUrl)
                .into(binding.image)

        }
    }

    class ProgressVH(binding: ItemProgressListBinding) : RecyclerView.ViewHolder(binding.root)
}