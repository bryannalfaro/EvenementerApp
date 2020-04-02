package com.example.evenementerapp.network.objects

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class User (
    @Json(name = "UserName")
    val userName: String,
    @Json(name = "Password")
    val password: String,
    @Json(name = "Name")
    val Name: String,
    @Json(name = "LastName")
    val LastName: String,
    @Json(name = "Email")
    val Email: String,
    @Json(name = "Photo")
    val Photo: String
)

data class LoginRequest (
    @Json(name = "UserName")
    var userName: String,

    @Json(name = "Password")
    var password: String
)

data class LoginResponse (

    var message: String,

    var user: String?
)

data class UserSingleResponse (
    var user: String?
)