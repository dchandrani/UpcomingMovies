package com.app.upcomingmovies.ui.info

import android.os.Bundle
import android.view.View
import com.app.upcomingmovies.R
import com.app.upcomingmovies.ui.base.BaseFragment

class InformationFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(getString(R.string.menu_info))
    }

    override fun getLayout(): Int = R.layout.information_fragment

    override fun getHasOptionsMenu(): Boolean = false

    override fun setTitle(title: String) {
        tvTitle?.text = title
    }
}