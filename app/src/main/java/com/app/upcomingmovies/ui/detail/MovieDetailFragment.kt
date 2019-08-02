package com.app.upcomingmovies.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.app.upcomingmovies.R
import com.app.upcomingmovies.ui.base.BaseFragment
import com.app.upcomingmovies.util.gone
import com.app.upcomingmovies.util.toast
import com.app.upcomingmovies.util.visible
import kotlinx.android.synthetic.main.movie_detail_fragment.*
import org.koin.android.ext.android.getKoin
import org.koin.android.viewmodel.ext.android.getViewModel

class MovieDetailFragment : BaseFragment() {
    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.movie_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.run {
            with(MovieDetailFragmentArgs.fromBundle(this)) {
                getKoin().setProperty("id", id)
                setTitle(titile)
            }
        }

        viewModel = getViewModel()

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

    override fun getLayout(): Int = R.id.movieDetailFragment

    override fun getHasOptionsMenu(): Boolean = false

    override fun setTitle(title: String) {
        tvTitle?.text = title
    }
}