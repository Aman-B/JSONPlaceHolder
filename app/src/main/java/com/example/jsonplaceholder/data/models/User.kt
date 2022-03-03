package com.example.jsonplaceholder.data.models

import com.squareup.moshi.Json
import java.io.Serializable

/**
 * Data class for defining the user model.
 * Declared serializable because it needs to be passed to other fragment.
 */
data class User(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "username") val username: String,
    @field:Json(name = "email") val email: String,
    @field:Json(name = "phone") val phone: String,
    @field:Json(name = "website") val website: String
) : Serializable


