package com.example.letssopt.core.local.retrofit

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetUserInfoResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
    @SerialName("code")
    val code: String,
    @SerialName("data")
    val data: GetUserData? = null,
    @SerialName("meta")
    val meta: MetaList? = null
)

@Serializable
data class GetUserData(
    @SerialName("id")
    val id: Int,
    @SerialName("loginId")
    val loginId: String,
    @SerialName("name")
    val name: String,
    @SerialName("email")
    val email: String,
    @SerialName("age")
    val age: Int,
    @SerialName("part")
    val part: String
)


