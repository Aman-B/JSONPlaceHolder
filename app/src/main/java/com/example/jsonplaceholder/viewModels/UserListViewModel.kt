package com.example.jsonplaceholder.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jsonplaceholder.data.models.UserModel
import com.example.jsonplaceholder.data.repositories.UserRepository

class UserListViewModel : ViewModel() {
    private val userRepository = UserRepository.getInstance()

    //get Users from JSONPlaceHolder remote source.
    val userList: MutableLiveData<List<UserModel>> = userRepository.getUsersFromJSONPlaceHolder()
}