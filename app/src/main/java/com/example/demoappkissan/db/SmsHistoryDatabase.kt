package com.example.demoappkissan.db

import android.content.Context
import androidx.room.*
import com.example.demoappkissan.db.dao.SmsHistoryDao
import com.example.demoappkissan.db.entity.SmsSentHistoryEntity


@Database(entities = [SmsSentHistoryEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class SmsHistoryDatabase : RoomDatabase() {
    abstract fun smsHistoryDao(): SmsHistoryDao?

    companion object {
        private var instance: SmsHistoryDatabase? = null
        @Synchronized
        fun getInstance(context: Context): SmsHistoryDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    SmsHistoryDatabase::class.java,
                    "note_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }
}
