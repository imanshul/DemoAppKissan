package com.example.demoappkissan.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.demoappkissan.R

/*
*Created By Anshul Thakur on 2/15/2020.
*/
fun openFragment(act: FragmentActivity?, fragment: Fragment,  shouldAdd: Boolean) {
    act?.apply {
        val manager = supportFragmentManager
        val ft = manager.beginTransaction()

        ft.replace(R.id.container, fragment, fragment::class.java.canonicalName)
        if (shouldAdd)
            ft.addToBackStack(fragment::class.java.canonicalName)
        ft.commit()
    }
}