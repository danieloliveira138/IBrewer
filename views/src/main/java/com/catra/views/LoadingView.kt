package com.catra.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout

class LoadingView(
    context: Context,
    attrs: AttributeSet
) : ConstraintLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.loading_view_layout, this)
    }
}