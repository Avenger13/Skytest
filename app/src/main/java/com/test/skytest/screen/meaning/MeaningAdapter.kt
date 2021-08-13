package com.test.skytest.screen.meaning

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.test.skytest.data.network.api.search.response.MeaningFull
import com.test.skytest.databinding.ItemMeaningListBinding


class MeaningAdapter(private val picasso: Picasso) :
    RecyclerView.Adapter<MeaningAdapter.MeaningVH>() {

    private val meanings = mutableListOf<MeaningFull>()

    fun setMeanings(data: List<MeaningFull>) {
        meanings.clear()
        meanings.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeaningVH {
        return MeaningVH(
            picasso,
            ItemMeaningListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MeaningVH, position: Int) {
        holder.bind(meanings[position])
    }

    override fun getItemCount(): Int {
        return meanings.size
    }

    class MeaningVH(
        private val picasso: Picasso,
        private val binding: ItemMeaningListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(meaning: MeaningFull) {
            binding.text.text = meaning.text
            binding.translation.text = meaning.translation.text

            val imageUrl =
                "https://" + meaning.images.firstOrNull()?.url?.takeIf { it.length > 2 }
                    ?.substring(2)

            picasso.load(imageUrl)
                .into(binding.image)
        }
    }

}