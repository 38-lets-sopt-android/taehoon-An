package com.example.letssopt.ui.home.profile

import com.example.letssopt.core.local.model.ProfileContentItem
import com.example.letssopt.ui.util.EventStatus

data class ProfileUiState(
    val profileContent : ProfileContentItem?,

    val profileStatus: EventStatus = EventStatus.Idle
)
