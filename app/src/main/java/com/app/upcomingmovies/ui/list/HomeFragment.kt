package com.app.upcomingmovies.ui.list

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.upcomingmovies.R
import com.app.upcomingmovies.response.Movie
import com.app.upcomingmovies.util.toast
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment: Fragment() {
    private val mMovieList = mutableListOf<Movie>()
    private val mMoviesAdapter = MoviesAdapter(mMovieList, ::onMovieClicked)
    private val tvTitle: TextView? by lazy {
        activity?.findViewById<TextView>(R.id.tvTitle)
    }
    private lateinit var viewModel: MovieListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this).get(MovieListViewModel::class.java)
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvTitle?.text = getString(R.string.app_name)

        rvMovies.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
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

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId ==  R.id.action_info){
            val action = HomeFragmentDirections.actionHomeFragmentToInformationFragment()
            findNavController().navigate(action)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onMovieClicked(movieId: Long) {
        requireContext().toast("Movie clicked: $movieId")
    }
}