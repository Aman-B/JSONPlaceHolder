package com.example.jsonplaceholder.data.api

import com.example.jsonplaceholder.data.models.PostModel
import com.example.jsonplaceholder.data.models.UserModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
/**
 * Endpoints for api are defined here.
 */
interface UserEndpoints {

    @GET("users")
    fun getUserList(): Call<List<UserModel>>

    @GET("posts")
    fun getPostsByUserId(@Query("userId") userId: Int): Call<List<PostModel>>

}