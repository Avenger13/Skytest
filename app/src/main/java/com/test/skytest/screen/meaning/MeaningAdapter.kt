package com.test.skytest.screen.meaning

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.test.skytest.appComponent
import com.test.skytest.data.network.api.search.response.MeaningFull
import com.test.skytest.databinding.ItemMeaningListBinding


class MeaningAdapter :
    ListAdapter<MeaningFull, MeaningAdapter.MeaningVH>(MeaningFullDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeaningVH {
        return MeaningVH(
            ItemMeaningListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MeaningVH, position: Int) {
        holder.bind(getItem(position))
    }

    class MeaningVH(
        private val binding: ItemMeaningListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(meaning: MeaningFull) {
            binding.text.text = meaning.text
            binding.translation.text = meaning.translation.text

            val imageUrl =
                "https://" + meaning.images.firstOrNull()?.url?.takeIf { it.length > 2 }
                    ?.substring(2)

            itemView.context.appComponent.picasso.load(imageUrl)
                .into(binding.image)
        }
    }

    object MeaningFullDiffCallback : DiffUtil.ItemCallback<MeaningFull>() {
        override fun areItemsTheSame(oldItem: MeaningFull, newItem: MeaningFull) =
            oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: MeaningFull, newItem: MeaningFull) =
            oldItem == newItem

    }
}