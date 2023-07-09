package com.example.moviespractice.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviespractice.R
import com.example.moviespractice.adapters.ViewPagerAdapter
import com.example.moviespractice.fragments.FragmentMovies
import com.example.moviespractice.fragments.FragmentTop
import com.example.moviespractice.fragments.FragmentUpcoming
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(FragmentMovies(), getString(R.string.tab_one))
        adapter.addFragment(FragmentTop(), getString(R.string.tab_two))
        adapter.addFragment(FragmentUpcoming(), getString(R.string.tab_three))
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }


}