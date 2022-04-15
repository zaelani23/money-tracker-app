package com.segalahal.moneytrackerapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.segalahal.moneytrackerapp.data.RecordRepository
import com.segalahal.moneytrackerapp.di.Injection
import com.segalahal.moneytrackerapp.ui.detail.DetailViewModel
import com.segalahal.moneytrackerapp.ui.input.InputViewModel
import com.segalahal.moneytrackerapp.ui.main.MainViewModel

class ViewModelFactory(private val recordRepository: RecordRepository): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(InputViewModel::class.java) -> {
                InputViewModel(recordRepository) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(recordRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(recordRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }
}