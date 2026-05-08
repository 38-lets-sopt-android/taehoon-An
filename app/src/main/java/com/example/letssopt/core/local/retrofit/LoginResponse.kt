package com.example.letssopt.core.local.retrofit

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
    @SerialName("code")
    val code: String,
    @SerialName("data")
    val data: Data? = null,
    @SerialName("meta")
    val meta: MetaList? = null
)

@Serializable
data class Data(
    @SerialName("userId")
    val userId: Int
)

