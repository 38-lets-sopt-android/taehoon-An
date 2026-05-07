package com.example.letssopt.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.letssopt.data.local.BuyingItemDao

class MainViewModelFactory(
    private val dao: BuyingItemDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
