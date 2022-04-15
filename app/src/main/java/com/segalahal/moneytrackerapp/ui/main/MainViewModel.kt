package com.segalahal.moneytrackerapp.ui.main

import androidx.lifecycle.ViewModel
import com.segalahal.moneytrackerapp.data.RecordRepository

class MainViewModel(private val recordRepository: RecordRepository): ViewModel() {
    fun getEarnings() = recordRepository.getEarnings()

    fun getMyMoney() = recordRepository.getMyMoney()

    fun getExpense() = recordRepository.getExpense()

    fun getRecords() = recordRepository.getRecords()
}