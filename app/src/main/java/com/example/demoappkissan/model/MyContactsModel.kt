package com.example.demoappkissan.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class MyContactsModel(
    @SerializedName("contactDetails")
    var contactDetails: List<ContactDetails>? = null
)

data class ContactDetails(
    @SerializedName("contact")
    var contact: String,
    @SerializedName("first_name")
    var firstName: String? = null,
    @SerializedName("last_name")
    var lastName: String? = null
):Serializable