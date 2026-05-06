package com.example.letssopt.ui.signup

sealed interface SignUpSideEffect {
    data class ShowToast(val message: String, val duration: Int) : SignUpSideEffect
    data class ShowSnack(val message: String) : SignUpSideEffect
    data object CompleteSignUp : SignUpSideEffect
}
