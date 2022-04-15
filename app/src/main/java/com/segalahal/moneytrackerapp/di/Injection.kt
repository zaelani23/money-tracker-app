package com.segalahal.moneytrackerapp.di

import android.content.Context
import com.segalahal.moneytrackerapp.data.DataSource
import com.segalahal.moneytrackerapp.data.RecordRepository
import com.segalahal.moneytrackerapp.data.room.MoneyTrackerDatabase
import com.segalahal.moneytrackerapp.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): RecordRepository{
        val database = MoneyTrackerDatabase.getInstance(context)
        val localDataSource = DataSource.getInstance(database.recordDao())
        val appExecutors = AppExecutors()
        return RecordRepository.getInstance(localDataSource, appExecutors)
    }
}