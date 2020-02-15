package com.example.demoappkissan.db

import androidx.room.TypeConverter
import com.example.demoappkissan.constants.SuccessFailureConstants

/*
*Created By Anshul Thakur on 2/15/2020.
*/

class Converters {

    @TypeConverter
    fun toStatus(value: String) = enumValueOf<SuccessFailureConstants>(value)

    @TypeConverter
    fun fromStatus(value: SuccessFailureConstants) = value.name
}