package com.app.upcomingmovies.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast

fun ImageView.setImage(url: String) {
//    Glide.with(context).load("http://image.tmdb.org/t/p/w185/$url").into(this)
//    GlideModule.wi

    GlideApp.with(this)
        .load(url)
        .into(this)

}

fun ViewGroup.inflate(layout: Int): View = LayoutInflater.from(context).inflate(layout, this, false)

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}