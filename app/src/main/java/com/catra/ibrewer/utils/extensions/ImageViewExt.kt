package com.catra.ibrewer.utils.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

infix fun ImageView.setImage(url: String) =
    Glide.with(this.context)
        .load(url)
        .into(this)