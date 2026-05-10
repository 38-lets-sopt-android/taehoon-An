package com.example.letssopt.core.local.model

import androidx.compose.runtime.Immutable

@Immutable
data class ProfileContentItem(
    val textId: String? = "",
    val textName: String? = "",
    val textEmail: String? = "",
    val textAge: Int? = 0,
    val textPart: String? = ""
)
