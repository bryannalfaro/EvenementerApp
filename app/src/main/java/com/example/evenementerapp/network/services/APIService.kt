package com.example.evenementerapp.network.services

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.ConnectionSpec
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*
import java.util.logging.Level

//TODO: Cambiar la ip a la de su compu
private const val BASE_URL = "http://192.168.1.8:3000/api/"
private const val POSTS_URL = "posts"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val lists = Arrays.asList(
    ConnectionSpec.COMPATIBLE_TLS,
    ConnectionSpec.CLEARTEXT,
    ConnectionSpec.MODERN_TLS
)
private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
private val client = OkHttpClient.Builder().connectionSpecs(lists).addInterceptor(interceptor).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .baseUrl(BASE_URL)
    .build()

object APIService {
    val retrofitEventService: EventService by lazy {
        retrofit.create(EventService::class.java)
    }
    val retrofitUserService: UserService by lazy {
        retrofit.create(UserService::class.java)
    }
    // TODO: More services...
}

data class EncryptedData (
    var data: String
)