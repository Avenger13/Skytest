package com.test.skytest.screen.search

import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.test.skytest.appComponent
import com.test.skytest.data.network.api.search.response.Word
import com.test.skytest.databinding.ActivitySearchBinding
import com.test.skytest.presentation.BaseMvpActivity
import com.test.skytest.screen.meaning.MeaningActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class SearchActivity : BaseMvpActivity(), SearchView {

    @Inject
    lateinit var presenterProvider: Provider<SearchPresenter>
    private val presenter by moxyPresenter { presenterProvider.get() }

    private lateinit var binding: ActivitySearchBinding
    private lateinit var wordAdapter: WordAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        wordAdapter = WordAdapter(presenter::onWordClick)

        binding.search.addTextChangedListener(
            afterTextChanged = { presenter.onSearch(it?.toString()) }
        )

        with(binding.words) {
            adapter = wordAdapter
            layoutManager = LinearLayoutManager(this@SearchActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@SearchActivity,
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
        startActivity(MeaningActivity.create(this, ids))
    }
}