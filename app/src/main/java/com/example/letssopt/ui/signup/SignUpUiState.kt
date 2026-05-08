package com.example.letssopt.ui.signup

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
