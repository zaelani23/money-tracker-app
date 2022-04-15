package com.segalahal.moneytrackerapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.segalahal.moneytrackerapp.data.entity.RecordEntity
import com.segalahal.moneytrackerapp.utils.AppExecutors
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class RecordRepository private constructor(
    private val localDataSource : DataSource,
    private val appExecutors: AppExecutors
): RecordDataSource{
    override fun getRecords(): LiveData<List<RecordEntity>> {
        val records = MutableLiveData<List<RecordEntity>>()
        records.postValue(runBlocking{
            GlobalScope.async{ localDataSource.getRecords() }.await()
        })
        return records
    }

    override fun getRecordById(id: Int): LiveData<RecordEntity> {
        val record = MutableLiveData<RecordEntity>()
        record.postValue(runBlocking {
            GlobalScope.async { localDataSource.getRecordById(id) }.await()
        })
        return record
    }

    override fun getEarnings(): LiveData<Int> {
        val earnings = MutableLiveData<Int>()
        earnings.postValue(runBlocking {
            GlobalScope.async { localDataSource.getEarnings() }.await()
        })
        return earnings
    }

    override fun getExpense(): LiveData<Int> {
        val expense = MutableLiveData<Int>()
        expense.postValue(runBlocking {
            GlobalScope.async { localDataSource.getExpense() }.await()
        })
        return expense
    }

    override fun getMyMoney(): LiveData<Int> {
        val myMoney = MutableLiveData<Int>()
        myMoney.postValue(runBlocking {
            GlobalScope.async { localDataSource.getMyMoney() }.await()
        })
        return myMoney
    }

    fun insertRecord(recordEntity: RecordEntity){
        appExecutors.diskIO().execute{
            localDataSource.insertRecord(recordEntity)
        }
    }

    fun deleteRecord(recordEntity: RecordEntity){
        appExecutors.diskIO().execute{
            localDataSource.deleteRecord(recordEntity)
        }
    }

    companion object {
        @Volatile
        private var instance: RecordRepository? = null
        fun getInstance(localDataSource: DataSource, appExecutors: AppExecutors): RecordRepository =
            instance ?: synchronized(this) {
                instance ?: RecordRepository(localDataSource, appExecutors)
            }
    }
}