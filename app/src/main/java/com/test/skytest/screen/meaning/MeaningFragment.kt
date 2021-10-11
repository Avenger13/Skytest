package com.test.skytest.screen.meaning

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import by.kirich1409.viewbindingdelegate.viewBinding
import com.test.skytest.App
import com.test.skytest.R
import com.test.skytest.data.network.api.search.response.MeaningFull
import com.test.skytest.databinding.FragmentMeaningBinding
import com.test.skytest.di.meaning.MeaningPresenterFactory
import com.test.skytest.presentation.BaseMvpFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject


class MeaningFragment : BaseMvpFragment(R.layout.fragment_meaning), MeaningView {

    private val args: MeaningFragmentArgs by navArgs()

    @Inject
    lateinit var meaningPresenterFactory: MeaningPresenterFactory

    private val presenter by moxyPresenter { meaningPresenterFactory.create(args.meaningIds) }

    private val binding by viewBinding(FragmentMeaningBinding::bind)
    private var meaningAdapter: MeaningAdapter? = null

    override fun onAttach(context: Context) {
        App.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        meaningAdapter = MeaningAdapter()
        with(binding.meanings) {
            adapter = meaningAdapter
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        meaningAdapter = null
    }

    override fun showMeanings(meanings: List<MeaningFull>) {
        meaningAdapter?.submitList(meanings)
    }
}