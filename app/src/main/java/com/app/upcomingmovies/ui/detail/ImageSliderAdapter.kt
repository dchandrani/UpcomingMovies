package com.app.upcomingmovies.ui.detail

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import coil.api.load
import com.app.upcomingmovies.R
import com.app.upcomingmovies.response.MovieImage
import com.app.upcomingmovies.utils.inflate

class ImageSliderAdapter(private val images: List<MovieImage>) : PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun getCount(): Int = images.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val rootView = container.inflate(R.layout.item_image_slider)
        val posterImage = rootView.findViewById<ImageView>(R.id.ivPoster)
        posterImage.load(images[position].getImagePath())
        container.addView(rootView)
        return rootView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}