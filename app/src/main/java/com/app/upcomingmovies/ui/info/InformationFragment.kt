package com.app.upcomingmovies.ui.info

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.app.upcomingmovies.R

class InformationFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.information_fragment, container, false)
    }
}