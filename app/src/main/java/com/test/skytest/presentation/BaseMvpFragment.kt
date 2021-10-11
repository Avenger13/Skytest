package com.test.skytest.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.skytest.App
import com.test.skytest.di.AppComponent
import com.test.skytest.ui.extension.toast
import moxy.MvpAppCompatFragment


open class BaseMvpFragment(contentLayoutId: Int) : MvpAppCompatFragment(contentLayoutId), BaseView {
    protected val appComponent: AppComponent
        get() = App.appComponent

    override fun showError(text: String) {
        requireContext().toast(text)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        log("onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log("onCreate, savedInstanceState = $savedInstanceState")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        log("onCreateView, savedInstanceState = $savedInstanceState")

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        log("onViewCreated, savedInstanceState = $savedInstanceState")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        log("onActivityCreated, savedInstanceState = $savedInstanceState")

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        log("onViewStateRestored, savedInstanceState = $savedInstanceState")
    }

    override fun onStart() {
        super.onStart()
        log("onStart")
    }

    override fun onResume() {
        super.onResume()
        log("onResume")

    }

    override fun onPause() {
        super.onPause()
        log("onPause")

    }

    override fun onStop() {
        super.onStop()
        log("onStop")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        log("onSaveInstanceState, outState = $outState")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        log("onDestroyView")

    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        log("onDetach")
    }

    private fun log(s: String) {
        Log.d("LifecycleCallbackMethod", "${javaClass.simpleName} $s")
    }
}