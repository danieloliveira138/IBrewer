package com.catra.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout

class ErrorView(
    context: Context,
    attrs: AttributeSet
) : ConstraintLayout(context, attrs) {

    private val errorLabel by lazy { this.findViewById<AppCompatTextView>(R.id.errorAdvise) }

    private val retryButton by lazy { this.findViewById<ImageButton>(R.id.tryAgainButton) }

    init {
        View.inflate(context, R.layout.error_view_layout, this)

        this.visibility = INVISIBLE
    }

    fun error(message: String, onRetry: () -> Unit) {
        visibility = VISIBLE
        errorLabel.text = message
        retryButton.setOnClickListener {
            onRetry.invoke()
            clear()
        }
    }

    fun clear() {
        this@ErrorView.visibility = INVISIBLE
    }
}