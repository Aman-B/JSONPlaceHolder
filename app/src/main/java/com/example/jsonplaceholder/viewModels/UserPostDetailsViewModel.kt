package com.example.jsonplaceholder.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jsonplaceholder.data.models.PostModel
import com.example.jsonplaceholder.data.repositories.UserRepository

class UserPostDetailsViewModel constructor(userID: Int) : ViewModel() {

    private val userRepository = UserRepository.getInstance()
    var postList: MutableLiveData<List<PostModel>> = MutableLiveData<List<PostModel>>()

    fun getPostsForUserIDFromRepository(): LiveData<List<PostModel>> {
        return postList
    }

    init {
        postList = userRepository.getPostsByUserID(userID)
    }
}