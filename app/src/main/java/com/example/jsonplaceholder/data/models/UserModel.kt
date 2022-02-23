package com.example.jsonplaceholder.data.models

import com.squareup.moshi.Json
import java.io.Serializable

/**
 * Data class for defining the user model.
 */
data class UserModel(
    @field:Json(name = "id") val id: Int = 0,
    @field:Json(name = "name") val name: String = "dummy Name",
    @field:Json(name = "username") val username: String = "dummyUserName",
    @field:Json(name = "email") val email: String = "dummyEmail",
    @field:Json(name = "phone") val phone: String = "dummyPhone",
    @field:Json(name = "website") val website: String = "dummyWebsite"
) : Serializable


