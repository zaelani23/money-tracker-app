package com.segalahal.moneytrackerapp.data.room

import androidx.room.*
import com.segalahal.moneytrackerapp.data.entity.RecordEntity

@Dao
interface RecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecord(record : RecordEntity)

    @Delete
    fun deleteRecord(record: RecordEntity)

    @Query("SELECT * FROM tracker_record ORDER BY date DESC")
    fun getRecords(): List<RecordEntity>

    @Query("SELECT * FROM tracker_record WHERE id = :id")
    fun getRecordById(id: Int): RecordEntity

    @Query("SELECT SUM(amount) as earnings FROM tracker_record WHERE amount > 0")
    fun getEarnings(): Int

    @Query("SELECT SUM(amount) as expense FROM tracker_record WHERE amount < 0")
    fun getExpense(): Int

    @Query("SELECT SUM(amount) as expense FROM tracker_record")
    fun getMyMoney(): Int
}