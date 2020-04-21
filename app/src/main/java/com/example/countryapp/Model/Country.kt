package com.example.countryapp.Model

import com.squareup.moshi.Json

data class Country(
    @field:Json(name = "name")
    val countryName: String?,
    @field:Json(name = "capital")
    val capital: String?,
    @field:Json(name = "flagPNG")
    val flag: String?
)