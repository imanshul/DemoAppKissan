package com.example.demoappkissan
import com.example.demoappkissan.utils.Validator
import org.junit.Assert.*;
import org.junit.Test

/*
*Created By Anshul Thakur on 2/15/2020.
*/

class PhoneNumberValidationTest{

    @Test
    fun phoneNumberValidator_CorrectNumber_ReturnsTrue(){
        assertTrue(Validator.isValidPhoneNumber("+917018700810"))
    }

    @Test
    fun phoneNumberValidator_ShortNumber_ReturnsFalse(){
        assertFalse(Validator.isValidPhoneNumber("12235"))
    }

    @Test
    fun phoneNumberValidator_LongNumber_ReturnsFalse(){
        assertFalse(Validator.isValidPhoneNumber("122353274326463"))
    }

    @Test
    fun phoneNumberValidator_ShortNumberWithCountryCode_ReturnsFalse(){
        assertFalse(Validator.isValidPhoneNumber("+9132333445568"))
    }

    @Test
    fun phoneNumberValidator_PhoneNumberWithForeignCountryCode_ReturnsTrue(){
        assertTrue(Validator.isValidPhoneNumber("+13238455677"))
    }
}