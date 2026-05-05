package com.example.letssopt.data.local.model

import androidx.compose.runtime.Immutable

@Immutable
data class WatchPartyItem(
    val image: Int,
    val startLabel: String,
    val hashTag: List<String>
)
