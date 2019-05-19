package com.app.upcomingmovies.ui.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.upcomingmovies.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
    }
}
