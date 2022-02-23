package com.example.jsonplaceholder

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserEndpoints {

    @GET("users")
    fun getUserList(): Call<List<UserModel>>

    @GET("posts")
    fun getPostsByUserId(@Query("userId") userId: Int): Call<List<PostModel>>

}