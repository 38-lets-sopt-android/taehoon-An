package com.example.letssopt.core.local.retrofit

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("api/v1/auth/signup")
    suspend fun signUp(@Body request: SignUpRequest): Response<SignUpResponse>

    @POST("api/v1/auth/signin")
    suspend fun signIn(@Body request: LoginRequest): Response<LoginResponse>

    @GET("/api/v1/users/{userId}")
    suspend fun getUserInfo(@Path("userId") userId: Int): Response<GetUserInfoResponse>
}
