package com.example.demoappkissan.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.demoappkissan.R
import com.example.demoappkissan.model.ContactDetails
import com.example.demoappkissan.utils.openFragment
import com.example.demoappkissan.view.contactdetails.ContactDetailsFragment

class ReusableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reusable)

        val frag = ContactDetailsFragment()
        if(intent.hasExtra("data"))
        {
            val data:ContactDetails = intent.getSerializableExtra("data") as ContactDetails
            frag.data = data
        }
        openFragment(this,frag,false)
    }

    override fun onSupportNavigateUp(): Boolean {
        if(supportFragmentManager.backStackEntryCount>0){
            supportFragmentManager.popBackStack()
            return true
        }else {
            return super.onSupportNavigateUp()
        }
    }
}
