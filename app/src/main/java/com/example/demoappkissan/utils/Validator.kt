package com.example.demoappkissan.utils

import java.util.regex.Pattern

/*
*Created By Anshul Thakur on 2/15/2020.
*/

object Validator {

    fun isValidPhoneNumber(number:String):Boolean{
        val pattern = Pattern.compile("^[+][0-9]{10,12}$")
       return pattern.matcher(number).matches()
    }
}