package com.app.upcomingmovies.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.navOptions
import com.app.upcomingmovies.R

abstract class BaseFragment : Fragment() {
    protected val tvTitle: TextView? by lazy {
        activity?.findViewById<TextView>(R.id.tvTitle)
    }

    protected val options = navOptions {
        anim {
            enter = R.anim.fade_in
            exit = R.anim.fade_out
            popEnter = R.anim.fade_in
            popExit = R.anim.fade_out
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(getHasOptionsMenu())
        return inflater.inflate(getLayout(), container, false)
    }

    protected abstract fun getHasOptionsMenu(): Boolean

    protected abstract fun getLayout(): Int

    protected abstract fun setTitle(title: String)
}