package com.example.acsassignment.common.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.acsassignment.manager.MainManager
import com.example.acsassignment.viewmodel.SharedViewModel

class SharedViewModelFactory (private val mainManager: MainManager) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
            return SharedViewModel(mainManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
