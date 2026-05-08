package com.example.letssopt.core.local.model

import androidx.compose.runtime.Immutable

@Immutable
data class AccountItem(
    val accountId : String? = "",
    val accountPw : String? = ""
)
