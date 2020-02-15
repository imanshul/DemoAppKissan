package com.example.demoappkissan.view.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.demoappkissan.db.entity.SmsSentHistoryEntity
import com.example.demoappkissan.model.ContactDetails
import com.example.demoappkissan.model.PojoSmsSendResponse
import com.example.demoappkissan.repo.SmsHistoryRepo


class HistoryViewModel(app:Application) :AndroidViewModel(app){

    private var repository: SmsHistoryRepo? = null
    private var allHistoryLiveData: LiveData<List<SmsSentHistoryEntity>?>? = null

    init {
        repository = SmsHistoryRepo(app)
        allHistoryLiveData = repository!!.allHistoryLiveData
    }

    fun insert(data: SmsSentHistoryEntity?) {
        repository?.insert(data)
    }

    fun delete(data: SmsSentHistoryEntity?) {
        repository?.delete(data)
    }

    fun getAllHistoryData(): LiveData<List<SmsSentHistoryEntity>?>? {
        return allHistoryLiveData
    }

    fun getSendSmsLiveData(): MutableLiveData<PojoSmsSendResponse> {
        return repository!!.sendSmsLiveData
    }

    fun sendSmsToMobile(contact:String,message:String, otp:String, data: ContactDetails){
        repository?.sendSmsToMobile(contact,message,otp,data)
    }

}