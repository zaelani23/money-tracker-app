package com.segalahal.moneytrackerapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tracker_record")
data class RecordEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "amount")
    val amount: Int,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "notes")
    val notes: String
)
