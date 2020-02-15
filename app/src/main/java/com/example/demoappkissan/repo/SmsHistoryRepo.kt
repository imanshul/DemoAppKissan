package com.example.demoappkissan.repo

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.demoappkissan.constants.SuccessFailureConstants
import com.example.demoappkissan.db.SmsHistoryDatabase
import com.example.demoappkissan.db.dao.SmsHistoryDao
import com.example.demoappkissan.db.entity.SmsSentHistoryEntity
import com.example.demoappkissan.model.ContactDetails
import com.example.demoappkissan.model.PojoSmsSendResponse
import com.example.demoappkissan.retrofit.RetrofitFactory
import com.example.demoappkissan.retrofit.getRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.util.*


class SmsHistoryRepo(application: Application) {
    private val smsHistoryDao: SmsHistoryDao?
    val allHistoryLiveData: LiveData<List<SmsSentHistoryEntity>?>?

    init {
        val database: SmsHistoryDatabase? = SmsHistoryDatabase.getInstance(application.applicationContext)
        smsHistoryDao = database!!.smsHistoryDao()
        allHistoryLiveData = smsHistoryDao?.getAllHistoryData()
    }

    fun insert(data: SmsSentHistoryEntity?) {
        CoroutineScope(Dispatchers.IO).launch {
            smsHistoryDao?.insert(history = data)
        }
    }

    fun delete(data: SmsSentHistoryEntity?) {
        CoroutineScope(Dispatchers.IO).launch {
            smsHistoryDao?.delete(history = data)
        }
    }

    val sendSmsLiveData = MutableLiveData<PojoSmsSendResponse>()
    fun sendSmsToMobile(contact: String, message: String, otp: String, data: ContactDetails) {
        val service = RetrofitFactory.makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.sendSms(message, contact)
            try {
                if (response.isSuccessful) {
                    //Do something with response e.g show to the UI.
                    insert(prepareDataToStoreInDB(message, otp, data, SuccessFailureConstants.SUCCESS, response.body()))
                    setValue(SuccessFailureConstants.SUCCESS,response.body())
                } else {
                    Log.d("anshul", "Error 1: ${response.code()}")
                    insert(prepareDataToStoreInDB(message, otp, data, SuccessFailureConstants.FAILURE))
                    setValue(SuccessFailureConstants.FAILURE,null)
                }
            } catch (e: HttpException) {
                Log.d("anshul", "Exception ${e.message}")
                insert(prepareDataToStoreInDB(message, otp, data, SuccessFailureConstants.FAILURE))
                setValue(SuccessFailureConstants.FAILURE,null)
            } catch (e: Throwable) {
                Log.d("anshul", "Ooops: Something else went wrong")
                insert(prepareDataToStoreInDB(message, otp, data, SuccessFailureConstants.FAILURE))
                setValue(SuccessFailureConstants.FAILURE,null)
            }
        }
    }

    private suspend fun setValue(status: SuccessFailureConstants, body: String?) {
        withContext(Dispatchers.Main){
            sendSmsLiveData.value = PojoSmsSendResponse(status,body)
        }
    }

    private fun prepareDataToStoreInDB(
        message: String,
        otp: String,
        data: ContactDetails,
        status: SuccessFailureConstants,
        ssid: String? = null
    ): SmsSentHistoryEntity {
        val smsData = SmsSentHistoryEntity()
        smsData.apply {
            messageBody = message
            this.otp = otp
            contactNumber = data.contact
            sentTime = Calendar.getInstance().timeInMillis
            statusCode = status
            fullName = "${data.firstName} ${data.lastName}"
            this.ssid = ssid
        }
        return smsData
    }
}