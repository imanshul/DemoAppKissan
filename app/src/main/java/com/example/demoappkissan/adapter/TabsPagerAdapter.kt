package com.example.demoappkissan.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.demoappkissan.R
import com.example.demoappkissan.view.home.ContactsFragment
import com.example.demoappkissan.view.home.HistoryFragment


class TabsPagerAdapter(context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm,FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    val titlesList = context.resources.getStringArray(R.array.tabs);

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return ContactsFragment()
            }
            1 -> {
                return HistoryFragment()
            }
            else -> {
                return ContactsFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titlesList[position]
    }

    override fun getCount(): Int = titlesList.size
}