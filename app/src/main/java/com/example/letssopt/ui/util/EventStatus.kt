package com.example.letssopt.ui.util

sealed class EventStatus {
    object Idle : EventStatus()
    object Loading : EventStatus()
}
