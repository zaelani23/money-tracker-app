package com.segalahal.moneytrackerapp.data

import com.segalahal.moneytrackerapp.data.entity.RecordEntity
import com.segalahal.moneytrackerapp.data.room.RecordDao

class DataSource(private val mRecordDao: RecordDao) {
    fun getRecords(): List<RecordEntity> = mRecordDao.getRecords()

    fun getRecordById(id: Int): RecordEntity = mRecordDao.getRecordById(id)

    fun getEarnings(): Int = mRecordDao.getEarnings()

    fun getExpense(): Int = mRecordDao.getExpense()

    fun getMyMoney(): Int = mRecordDao.getMyMoney()

    fun insertRecord(record: RecordEntity) = mRecordDao.insertRecord(record)

    fun deleteRecord(record: RecordEntity) = mRecordDao.deleteRecord(record)

    companion object {
        private var INSTANCE: DataSource? = null

        fun getInstance(mRecordDao: RecordDao): DataSource =
            INSTANCE ?: DataSource(mRecordDao)
    }
}