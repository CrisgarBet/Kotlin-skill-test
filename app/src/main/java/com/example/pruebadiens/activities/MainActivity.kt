package com.example.pruebadiens.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebadiens.R
import com.example.pruebadiens.adapters.ViewPagerAdapter
import com.example.pruebadiens.fragments.FragmentMovies
import com.example.pruebadiens.fragments.FragmentTop
import com.example.pruebadiens.fragments.FragmentUpcoming
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(FragmentMovies(), "Popular")
        adapter.addFragment(FragmentTop(), "Top Rated")
        adapter.addFragment(FragmentUpcoming(), "Upcoming")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }


}