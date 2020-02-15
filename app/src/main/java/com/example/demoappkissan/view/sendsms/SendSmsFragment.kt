package com.example.demoappkissan.view.sendsms


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.demoappkissan.R
import com.example.demoappkissan.constants.SuccessFailureConstants
import com.example.demoappkissan.extensions.showMessage
import com.example.demoappkissan.model.ContactDetails
import com.example.demoappkissan.utils.InternetCheckUtils
import com.example.demoappkissan.utils.RandomNumberUtils
import com.example.demoappkissan.view.home.HistoryViewModel
import kotlinx.android.synthetic.main.fragment_send_sms.*

class SendSmsFragment : Fragment(),View.OnClickListener {

    var data:ContactDetails? = null
    val otp:String by lazy { RandomNumberUtils.getRandomNumberString() }
    var viewModel: HistoryViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_send_sms, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setData()
        addObserver()
        setClicks()
    }

    @SuppressLint("SetTextI18n")
    private fun setData() {
        data?.apply {
            tvTo.text = contact
            tvMessageBody.text = "Hi Your OTP is : $otp"
        }
    }

    private fun addObserver() {
        if (viewModel != null) return

        viewModel = ViewModelProvider(this@SendSmsFragment).get(HistoryViewModel::class.java)

        viewModel?.getSendSmsLiveData()?.observe(viewLifecycleOwner, Observer {
            hideLoading()
            if(it.successFailureConstants == SuccessFailureConstants.SUCCESS){
                showMessage(context!!,"Sms sent successfully!")
            }else{
                showMessage(context!!,"Failed to send sms")
            }
            activity!!.finish()
        })
    }

    private fun setClicks(){
        btnSend.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0){
            btnSend->{
                if(InternetCheckUtils.isNetworkAvailable(context!!)){
                    data?.apply {
                        showLoading()
                        viewModel?.sendSmsToMobile(contact,tvMessageBody.text.toString(),otp,this)
                    }
                }else{
                    showMessage(context!!,"No internet connection!")
                }
            }
        }
    }

    private fun showLoading(){
        btnSend.visibility = View.GONE
        pbBar.visibility = View.VISIBLE
    }

    private fun hideLoading(){
        btnSend.visibility = View.VISIBLE
        pbBar.visibility = View.GONE
    }
}