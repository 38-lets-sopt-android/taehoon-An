package com.example.letssopt.ui.signup

import com.example.letssopt.ui.util.EventStatus

data class SignUpUiState(
    val textId: String = "",
    val textPw: String = "",
    val textCkPw: String = "",
    val textName: String = "",
    val textEmail: String = "",
    val textAge: String = "",
    val textPart: String = "",

    val signUpStatus: EventStatus = EventStatus.Idle
)
