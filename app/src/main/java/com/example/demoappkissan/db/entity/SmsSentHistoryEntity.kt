package com.example.demoappkissan.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.demoappkissan.constants.SuccessFailureConstants

@Entity(tableName = "sms_history")
class SmsSentHistoryEntity {
    @PrimaryKey(autoGenerate = true)
    var history_id:Int = 0
    var messageBody :String = ""
    var contactNumber:String = ""
    var sentTime:Long = 0L
    var fullName:String = ""
    var statusCode:SuccessFailureConstants = SuccessFailureConstants.FAILURE
    var otp:String = ""
    var ssid:String? = null
}
