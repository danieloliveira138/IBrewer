package com.catra.ibrewer.utils.extensions

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.catra.ibrewer.R
import com.google.android.material.appbar.CollapsingToolbarLayout

infix fun ImageView?.bind(url: String) =
    this?.let {
        Glide.with(it.context)
        .load(url)
        .into(this)
    }

infix fun TextView?.bind(text: String?) {
    this?.text = text ?: return
}

infix fun CollapsingToolbarLayout?.bind(text: String?) = this?.run {
    setCollapsedTitleTextColor(ContextCompat.getColor(this.context, R.color.white))
    setExpandedTitleColor(ContextCompat.getColor(this.context, R.color.colorPrimaryDark))
    title = text ?: ""
}