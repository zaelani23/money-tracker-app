package com.segalahal.moneytrackerapp.ui.input

import androidx.lifecycle.ViewModel
import com.segalahal.moneytrackerapp.data.RecordRepository
import com.segalahal.moneytrackerapp.data.entity.RecordEntity

class InputViewModel(private val recordRepository: RecordRepository) : ViewModel() {
    fun setRecord(recordEntity: RecordEntity){
        recordRepository.insertRecord(recordEntity)
    }
}