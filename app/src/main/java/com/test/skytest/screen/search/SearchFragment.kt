package com.test.skytest.screen.search

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.cachedIn
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.test.skytest.R
import com.test.skytest.databinding.FragmentSearchBinding
import com.test.skytest.presentation.BaseMvpFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

@ExperimentalCoroutinesApi
class SearchFragment : BaseMvpFragment(R.layout.fragment_search), SearchView {

    @Inject
    lateinit var presenterProvider: Provider<SearchPresenter>
    private val presenter by moxyPresenter { presenterProvider.get() }

    private val binding by viewBinding(FragmentSearchBinding::bind)
    private var wordAdapter: WordAdapter? = null

    override fun onAttach(context: Context) {
        appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.search.addTextChangedListener(
            afterTextChanged = { presenter.onSearch(it?.toString()) }
        )


        wordAdapter = WordAdapter(presenter::onWordClick)
        wordAdapter!!.addLoadStateListener { states: CombinedLoadStates ->
            binding.progress.isVisible = states.refresh == LoadState.Loading
            binding.words.isVisible = states.refresh != LoadState.Loading
        }

        viewLifecycleOwner.lifecycleScope.launch {
            presenter.words.cachedIn(this).collectLatest {
                wordAdapter?.submitData(it)
            }

        }

        with(binding.words) {
            adapter = wordAdapter!!
                .withLoadStateFooter(WordLoadStateAdapter(presenter::onRetry))

            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )

        }
    }


    override fun onDestroy() {
        super.onDestroy()
        wordAdapter = null
    }


    override fun refresh() {
        wordAdapter?.refresh()
    }

    override fun retry() {
        wordAdapter?.retry()
    }


    override fun showMeanings(ids: LongArray) {
        val action = SearchFragmentDirections.actionSearchFragmentToMeaningActivity(ids)
        findNavController().navigate(action)
    }

}