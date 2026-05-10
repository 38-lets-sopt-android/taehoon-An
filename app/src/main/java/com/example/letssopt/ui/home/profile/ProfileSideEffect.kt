package com.example.letssopt.ui.home.profile

sealed interface ProfileSideEffect {
    data class ShowToast(val message: String, val duration: Int) : ProfileSideEffect
}
