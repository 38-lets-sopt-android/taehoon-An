package com.example.letssopt.ui.home.profile

import androidx.lifecycle.ViewModel
import com.example.letssopt.core.local.model.ProfileContentItem
import com.example.letssopt.ui.util.EventStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel() : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState(
        profileContent = ProfileContentItem(
            textId = null,
            textName = null,
            textEmail = null,
            textAge = null,
            textPart = null
        ),
        profileStatus = EventStatus.Idle
        )
    )
    val uiState = _uiState.asStateFlow()

}
