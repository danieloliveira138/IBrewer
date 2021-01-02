package com.catra.ibrewer.ui.main

import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import com.catra.ibrewer.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainProvider {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun displayError(retry: () -> Unit) {
        errorView.error(getString(R.string.generic_error), retry)
    }

    override fun loading(hasShow: Boolean) {
        loadingView.visibility = if (hasShow) VISIBLE else INVISIBLE
    }
}