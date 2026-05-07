package com.example.letssopt.ui.login

sealed interface LoginSideEffect {
    data class ShowToast(val message: String, val duration: Int) : LoginSideEffect
    data object NavigateToMain : LoginSideEffect
}
