package com.example.demoappkissan.model

import com.example.demoappkissan.constants.SuccessFailureConstants

/*
*Created By Anshul Thakur on 2/15/2020.
*/

data class PojoSmsSendResponse(
    var successFailureConstants: SuccessFailureConstants,
    var ssid:String? = null
)