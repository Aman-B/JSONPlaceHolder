package com.example.jsonplaceholder.data.models

import com.squareup.moshi.Json

data class PostModel(
    @field:Json(name = "userId") val userId: Int = 0,
    @field:Json(name = "id") val id: Int = 0,
    @field:Json(name = "title") val title: String = "dummyTitle",
    @field:Json(name = "body") val body: String = "dummyBody"
)


