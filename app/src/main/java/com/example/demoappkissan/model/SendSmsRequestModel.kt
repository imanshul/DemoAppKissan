package com.example.demoappkissan.model

import com.google.gson.annotations.SerializedName

data class SendSmsRequestModel (
    @SerializedName("Body")
    val body:String,
    @SerializedName("To")
    val to:String
)