package com.app.upcomingmovies.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.upcomingmovies.R
import com.app.upcomingmovies.util.gone
import com.app.upcomingmovies.util.toast
import com.app.upcomingmovies.util.visible
import com.app.upcomingmovies.utils.ViewModelFactory
import kotlinx.android.synthetic.main.movie_detail_fragment.*

class MovieDetailFragment: Fragment() {
    private val tvTitle: TextView? by lazy {
        activity?.findViewById<TextView?>(R.id.tvTitle)
    }

    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val factory = ViewModelFactory(MovieDetailFragmentArgs.fromBundle(arguments!!).id)
        viewModel = ViewModelProviders.of(this, factory).get(MovieDetailViewModel::class.java)
        return inflater.inflate(R.layout.movie_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvTitle?.text = MovieDetailFragmentArgs.fromBundle(arguments!!).titile

        viewModel.error.observe(this, Observer {
            requireContext().toast(it)
        })

        viewModel.isLoading.observe(this, Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
            container.visibility = if (it) View.GONE else View.VISIBLE
        })

        viewModel.movieImages.observe(this, Observer {
            imageSlider.adapter = ImageSliderAdapter(it)
            if (it.size > 1) {
                pageIndicator.visible()
                pageIndicator.attachTo(imageSlider)
            } else {
                pageIndicator.gone()
            }
        })

        viewModel.movie.observe(this, Observer {
            it.run {
                tv_title.text = title
                tv_overview.text = overview
            }
        })
    }
}