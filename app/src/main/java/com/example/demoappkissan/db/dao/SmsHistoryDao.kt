package com.example.demoappkissan.db.dao

import android.provider.ContactsContract.CommonDataKinds.Note

import androidx.lifecycle.LiveData
import androidx.room.*

import com.example.demoappkissan.db.entity.SmsSentHistoryEntity

@Dao
interface SmsHistoryDao {

    @Insert
    fun insert(history: SmsSentHistoryEntity?)

    @Delete
    fun delete(history: SmsSentHistoryEntity?)

    @Query("SELECT * FROM sms_history ORDER BY sentTime DESC")
    fun getAllHistoryData(): LiveData<List<SmsSentHistoryEntity>?>?
}