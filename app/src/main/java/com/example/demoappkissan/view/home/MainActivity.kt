package com.example.demoappkissan.view.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.demoappkissan.R
import com.example.demoappkissan.adapter.TabsPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViewPager()
    }

    private fun setUpViewPager() {
        val tabsPagerAdapter = TabsPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = tabsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(view_pager)
    }
}
