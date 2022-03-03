package com.example.jsonplaceholder.data.api

import com.example.jsonplaceholder.data.models.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Endpoints for posts api are defined here.
 */
interface PostEndpoints {
    @GET("posts")
    fun getPostsByUserId(@Query("userId") userId: Int): Call<List<Post>>
}