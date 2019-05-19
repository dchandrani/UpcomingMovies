package com.app.upcomingmovies.ui.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.app.upcomingmovies.R

class HomeFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId ==  R.id.action_info){

            return true
        }
        return super.onOptionsItemSelected(item)
    }
}