package com.example.mvvmsample.util

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun ProgressBar.Show() {
    visibility = View.VISIBLE
}

fun ProgressBar.Hide() {
    visibility = View.GONE
}