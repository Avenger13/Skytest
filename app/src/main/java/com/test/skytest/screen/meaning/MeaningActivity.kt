package com.test.skytest.screen.meaning

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.test.skytest.appComponent
import com.test.skytest.data.network.api.search.response.MeaningFull
import com.test.skytest.databinding.ActivityMeaningBinding
import com.test.skytest.di.meaning.MeaningPresenterFactory
import com.test.skytest.presentation.BaseMvpActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject


class MeaningActivity : BaseMvpActivity(), MeaningView {

    @Inject
    lateinit var meaningPresenterFactory: MeaningPresenterFactory
    private val presenter by moxyPresenter { meaningPresenterFactory.create(meaningIds) }

    private val meaningIds: LongArray
        get() = intent.getLongArrayExtra(KEY_MEANING_ID) ?: longArrayOf()

    private lateinit var binding: ActivityMeaningBinding
    private lateinit var meaningAdapter: MeaningAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMeaningBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding.meanings) {
            adapter = meaningAdapter
            layoutManager = LinearLayoutManager(
                this@MeaningActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            addItemDecoration(
                DividerItemDecoration(
                    this@MeaningActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    @Inject
    fun createAdapter(picasso: Picasso) {
        meaningAdapter = MeaningAdapter(picasso)
    }

    override fun showMeanings(meanings: List<MeaningFull>) {
        meaningAdapter.setMeanings(meanings)
    }

    companion object {
        private const val KEY_MEANING_ID = "MEANING_ID"
        fun create(context: Context, meaningId: LongArray) =
            Intent(context, MeaningActivity::class.java)
                .putExtra(KEY_MEANING_ID, meaningId)
    }
}