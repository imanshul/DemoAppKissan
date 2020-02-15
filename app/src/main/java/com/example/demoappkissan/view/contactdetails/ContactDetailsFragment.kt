package com.example.demoappkissan.view.contactdetails


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.demoappkissan.R
import com.example.demoappkissan.extensions.showMessage
import com.example.demoappkissan.model.ContactDetails
import com.example.demoappkissan.utils.Validator
import com.example.demoappkissan.utils.openFragment
import com.example.demoappkissan.view.sendsms.SendSmsFragment
import kotlinx.android.synthetic.main.fragment_contact_details.*

class ContactDetailsFragment : Fragment(),View.OnClickListener {

    var data:ContactDetails? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_details, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setData()
        setClicks()
    }

    @SuppressLint("SetTextI18n")
    private fun setData() {
        data?.apply {
            tvName.text = "${firstName} ${lastName}"
            tvPhoneNumber.text = contact
        }
    }

    private fun setClicks() {
        btnSendOTP.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view){
            btnSendOTP->{
                if(!data?.contact.isNullOrEmpty() && Validator.isValidPhoneNumber(data!!.contact)) {
                    val frag = SendSmsFragment()
                    frag.data = data
                    openFragment(activity!!, frag, true)
                }else{
                    showMessage(context!!,getString(R.string.invalid_phone_number))
                }
            }
        }
    }
}
