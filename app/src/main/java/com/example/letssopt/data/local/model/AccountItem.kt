package com.example.letssopt.data.local.model

import androidx.compose.runtime.Immutable

@Immutable
data class AccountItem(
    val accountId : String? = "",
    val accountPw : String? = ""
)
