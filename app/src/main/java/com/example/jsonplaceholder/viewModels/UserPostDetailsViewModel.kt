package com.example.jsonplaceholder.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jsonplaceholder.data.models.PostModel
import com.example.jsonplaceholder.data.repositories.UserRepository

class UserPostDetailsViewModel constructor(userId: Int) : ViewModel() {

    private val userRepository = UserRepository.getInstance()

    val postList: MutableLiveData<List<PostModel>> = userRepository.getPostsByUserID(userId)
}