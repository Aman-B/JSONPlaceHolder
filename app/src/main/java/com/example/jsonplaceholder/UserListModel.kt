package com.example.jsonplaceholder

import com.squareup.moshi.Json

data class UserListModel(
    val id: Int,
    val name: String,
    @Json(name = "username") val userName: String,
    val email: String,
    val phone: String,
    val website: String
)


