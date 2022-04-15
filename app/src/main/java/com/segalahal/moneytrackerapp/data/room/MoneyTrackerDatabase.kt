package com.segalahal.moneytrackerapp.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.segalahal.moneytrackerapp.data.entity.RecordEntity

@Database(
    entities = [RecordEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MoneyTrackerDatabase : RoomDatabase() {
    abstract fun recordDao(): RecordDao

    companion object {
        @Volatile
        private var INSTANCE: MoneyTrackerDatabase? = null

        fun getInstance(context: Context): MoneyTrackerDatabase =
            INSTANCE?: synchronized(this){
                INSTANCE?:
                Room.databaseBuilder(context.applicationContext, MoneyTrackerDatabase::class.java, "moneytracker.db").build()
            }
    }
}