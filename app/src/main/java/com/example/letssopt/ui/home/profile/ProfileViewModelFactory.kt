package com.example.letssopt.ui.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.letssopt.core.local.PreferenceManager
import com.example.letssopt.ui.home.MainViewModel

class ProfileViewModelFactory(
    private val prefManager: PreferenceManager
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(prefManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
