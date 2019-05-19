package com.app.upcomingmovies.ui.info

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.app.upcomingmovies.R

class InformationFragment: Fragment() {
    private val tvTitle: TextView? by lazy {
        activity?.findViewById<TextView>(R.id.tvTitle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.information_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvTitle?.text = getString(R.string.menu_info)
    }
}