package com.example.letssopt.ui.login

import com.example.letssopt.ui.util.EventStatus

data class LoginUiState(
    val textId: String = "",
    val textPw: String = "",

    val loginStatus: EventStatus = EventStatus.Idle
)
