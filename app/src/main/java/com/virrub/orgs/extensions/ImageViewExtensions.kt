package com.virrub.orgs.extensions

import android.widget.ImageView
import coil.load
import com.virrub.orgs.R

fun ImageView.tryLoad(url: String?) {
    load(url) {
        placeholder(R.drawable.empty_img)
        fallback(R.drawable.empty_img)
        error(R.drawable.empty_img)
    }
}