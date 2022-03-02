package com.example.jsonplaceholder.data.models

import com.squareup.moshi.Json

/**
 * Data class for defining the post model.
 */
data class Post(
    @field:Json(name = "userId") val userId: Int,
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "body") val body: String
)


