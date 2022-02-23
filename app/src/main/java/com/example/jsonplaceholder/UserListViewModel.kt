package com.example.jsonplaceholder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserListViewModel : ViewModel() {

    private val userRepository = UserRespository.getInstance()

    val userList: MutableLiveData<List<UserModel>> = userRepository.getUsersFromJSONPlaceHolder()
}