package com.test.skytest.screen.search

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.skytest.R
import com.test.skytest.data.network.api.search.response.Word
import com.test.skytest.databinding.FragmentSearchBinding
import com.test.skytest.presentation.BaseMvpFragment
import com.test.skytest.screen.meaning.MeaningActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class SearchFragment : BaseMvpFragment(R.layout.fragment_search), SearchView {

    @Inject
    lateinit var presenterProvider: Provider<SearchPresenter>
    private val presenter by moxyPresenter { presenterProvider.get() }

    private lateinit var binding: FragmentSearchBinding
    private lateinit var wordAdapter: WordAdapter

    override fun onAttach(context: Context) {
        appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)
        binding.search.addTextChangedListener(
            afterTextChanged = { presenter.onSearch(it?.toString()) }
        )

        wordAdapter = WordAdapter(presenter::onWordClick)
        with(binding.words) {
            adapter = wordAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )

        }
    }


    override fun showProgress(show: Boolean) {
        wordAdapter.showProgress(show)
    }

    override fun showSearchResults(words: List<Word>) {
        wordAdapter.submitList(words)
    }

    override fun showMeanings(ids: LongArray) {
        startActivity(MeaningActivity.create(requireContext(), ids))
    }
}