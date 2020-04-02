package com.example.evenementerapp.network.objects

import android.icu.text.CaseMap
import com.squareup.moshi.Json

data class Event (
    @Json(name="Title")
    val title: String

)