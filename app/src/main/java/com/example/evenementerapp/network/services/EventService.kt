package com.example.evenementerapp.network.services

import com.example.evenementerapp.network.objects.Event
import retrofit2.Call
import retrofit2.http.GET

private const val PUB_URL = "publication/"

interface EventService {

    @GET(PUB_URL)
    fun getAllEvents():
            Call<List<Event>>

}