package com.segalahal.moneytrackerapp.data

import androidx.lifecycle.LiveData
import com.segalahal.moneytrackerapp.data.entity.RecordEntity

interface RecordDataSource {
    fun getRecords(): LiveData<List<RecordEntity>>

    fun getRecordById(id: Int): LiveData<RecordEntity>

    fun getEarnings(): LiveData<Int>

    fun getExpense(): LiveData<Int>

    fun getMyMoney(): LiveData<Int>
}