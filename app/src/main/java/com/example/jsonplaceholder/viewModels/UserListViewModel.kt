package com.example.jsonplaceholder.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jsonplaceholder.data.model.UserModel
import com.example.jsonplaceholder.data.repositories.UserRepository

class UserListViewModel : ViewModel() {

    private val userRepository = UserRepository.getInstance()

    val userList: MutableLiveData<List<UserModel>> = userRepository.getUsersFromJSONPlaceHolder()
}