package com.example.demoappkissan.retrofit

import com.example.demoappkissan.model.SendSmsRequestModel
import retrofit2.Response
import retrofit2.http.*

interface RetrofitService {

    @FormUrlEncoded
    @POST("/sms")
    suspend fun sendSms(@Field("Body") body: String, @Field("To")to:String): Response<String>

}