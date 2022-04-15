package com.segalahal.moneytrackerapp.ui.detail

import androidx.lifecycle.ViewModel
import com.segalahal.moneytrackerapp.data.RecordRepository
import com.segalahal.moneytrackerapp.data.entity.RecordEntity

class DetailViewModel(private val recordRepository: RecordRepository) : ViewModel() {
    fun getRecordById(id: Int) = recordRepository.getRecordById(id)

    fun deleteRecord(recordEntity: RecordEntity) = recordRepository.deleteRecord(recordEntity)
}