package com.example.jsonplaceholder.data.api

import com.example.jsonplaceholder.data.models.User
import retrofit2.Call
import retrofit2.http.GET

/**
 * Endpoints for api are defined here.
 */
interface UserEndpoints {
    @GET("users")
    fun getUserList(): Call<List<User>>
}