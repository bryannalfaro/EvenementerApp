package com.example.evenementerapp.network.objects

import com.squareup.moshi.Json

data class Event (
    @Json(name = "Title")
    val title: String,
    @Json(name = "Description")
    val description: String,
    @Json(name = "Price")
    val price: Int,
    @Json(name = "Picture")
    val picture: String,
    @Json(name = "Organizer")
    val organizer: User?,
    @Json(name = "Commentary")
    val commentary: List<String>?,
    @Json(name = "Likes")
    val likes: Int,
    @Json(name = "Participants")
    val participants: Int,
    @Json(name = "Guests")
    val guests: List<User>?
)
