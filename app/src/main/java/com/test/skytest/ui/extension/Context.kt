package com.test.skytest.ui.extension

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar


fun Context.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.snackBar(view: View, text: String) {
    Snackbar.make(this, view, text, Snackbar.LENGTH_LONG).show()
}