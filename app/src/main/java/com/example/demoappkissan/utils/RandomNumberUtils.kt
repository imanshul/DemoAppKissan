package com.example.demoappkissan.utils

import java.util.*

/*
*Created By Anshul Thakur on 2/15/2020.
*/
object RandomNumberUtils{

    /*
        This method will give 6 digit random number
        We used string as return type because the number can be 000000 which
        is equivalent to 0
    */

    fun getRandomNumberString(): String {
        // It will generate 6 digit random Number.
        val rnd = Random()
        val number = rnd.nextInt(999999)

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number)
    }
}