package com.example.evenementerapp.network.services

import com.example.evenementerapp.network.objects.Event
import retrofit2.Call
import retrofit2.http.GET

interface UserService {

    @GET("user/signUp")
    fun signUp():
            Call<Event>

}