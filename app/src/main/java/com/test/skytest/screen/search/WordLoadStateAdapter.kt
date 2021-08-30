package com.test.skytest.screen.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.skytest.databinding.ItemLoadStateBinding


class WordLoadStateAdapter(val onRetry: () -> Unit) :
    LoadStateAdapter<WordLoadStateAdapter.WordLoadStateVH>() {


    override fun onBindViewHolder(holder: WordLoadStateVH, loadState: LoadState) =
        holder.bind(loadState)


    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        WordLoadStateVH(ItemLoadStateBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    inner class WordLoadStateVH(private val binding: ItemLoadStateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            when (loadState) {
                is LoadState.NotLoading -> {
                    binding.error.isVisible = false
                    binding.progress.isVisible = false
                    binding.retry.isVisible = false
                }
                is LoadState.Loading -> {
                    binding.error.isVisible = false
                    binding.progress.isVisible = true
                    binding.retry.isVisible = false
                }
                is LoadState.Error -> {
                    binding.error.isVisible = true
                    binding.progress.isVisible = false
                    binding.retry.isVisible = true
                    binding.error.text = loadState.error.message
                }
            }

            binding.retry.setOnClickListener { onRetry() }
        }
    }
}