package com.example.evenementerapp.network.services

import com.example.evenementerapp.network.objects.LoginRequest
import com.example.evenementerapp.network.objects.LoginResponse
import com.example.evenementerapp.network.objects.User
import com.example.evenementerapp.network.objects.UserSingleResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

private const val LOGIN_URL = "user/"

interface UserService {

    @POST(LOGIN_URL + "signUp")
    fun signUp(@Body user: User):
            Call<UserSingleResponse>

    @POST(LOGIN_URL + "signIn")
    fun signIn(@Body loginRequest: LoginRequest):
            Call<LoginResponse>

}