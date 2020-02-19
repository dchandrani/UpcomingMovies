package com.app.upcomingmovies.ui.list

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.upcomingmovies.R
import com.app.upcomingmovies.response.Movie
import com.app.upcomingmovies.ui.base.BaseFragment
import com.app.upcomingmovies.utils.toast
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {
    private val mMovieList = mutableListOf<Movie>()

    private val mMoviesAdapter = MoviesAdapter(mMovieList, ::onMovieClicked)

    private val viewModel: MovieListViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle(getString(R.string.app_name))

        rvMovies.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = mMoviesAdapter
        }

        viewModel.movies.observe(this, Observer {
            with(mMovieList) {
                clear()
                addAll(it)
                mMoviesAdapter.notifyDataSetChanged()
            }
        })

        viewModel.isLoading.observe(this, Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.error.observe(this, Observer {
            requireContext().toast(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_info) {
            findNavController().navigate(item.itemId, null, options)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getHasOptionsMenu(): Boolean = true

    override fun setTitle(title: String) {
        tvTitle?.text = title
    }

    override fun getLayout(): Int = R.layout.home_fragment

    private fun onMovieClicked(movieId: Long, title: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(movieId, title)
        findNavController().navigate(action, options)
    }
}