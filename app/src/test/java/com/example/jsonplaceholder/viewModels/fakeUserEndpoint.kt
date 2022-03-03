package com.example.jsonplaceholder.viewModels

import com.example.jsonplaceholder.data.api.UserEndpoints
import com.example.jsonplaceholder.data.models.User
import retrofit2.Call
import retrofit2.http.GET

interface fakeUserEndpoint : UserEndpoints {

    @GET("users/1")
    override fun getUserList(): Call<List<User>>
}