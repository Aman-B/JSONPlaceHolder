package com.example.jsonplaceholder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserPostDetailsViewModel constructor(userId: Int) : ViewModel() {

    private val userRespository = UserRespository.getInstance()

    val postList: MutableLiveData<List<PostModel>> = userRespository.getPostsByUserID(userId)
}