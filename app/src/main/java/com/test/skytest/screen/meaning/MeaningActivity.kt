package com.test.skytest.screen.meaning

import android.os.Bundle
import androidx.navigation.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.test.skytest.App
import com.test.skytest.R
import com.test.skytest.data.network.api.search.response.MeaningFull
import com.test.skytest.databinding.FragmentMeaningBinding
import com.test.skytest.di.meaning.MeaningPresenterFactory
import com.test.skytest.presentation.BaseMvpActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject


class MeaningActivity : BaseMvpActivity(R.layout.fragment_meaning), MeaningView {

    private val args: MeaningActivityArgs by navArgs()

    @Inject
    lateinit var meaningPresenterFactory: MeaningPresenterFactory

    private val presenter by moxyPresenter { meaningPresenterFactory.create(args.meaningIds) }

    private val binding by viewBinding(FragmentMeaningBinding::bind)
    private val meaningAdapter = MeaningAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)

        with(binding.meanings) {
            adapter = meaningAdapter
            layoutManager = LinearLayoutManager(this@MeaningActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@MeaningActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }


    override fun showMeanings(meanings: List<MeaningFull>) {
        meaningAdapter.submitList(meanings)
    }
}