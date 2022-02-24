package com.example.jsonplaceholder.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jsonplaceholder.data.models.UserModel
import com.example.jsonplaceholder.data.repositories.UserRepository

/**
 * View model for UserListFragment
 */
class UserListViewModel : ViewModel() {
    private val userRepository = UserRepository.getInstance()
    var userList: MutableLiveData<List<UserModel>> = MutableLiveData<List<UserModel>>()

    //get Users from repository.
    fun getUsersFromRepository(): LiveData<List<UserModel>> {
        return userList
    }

    init {
        userList = userRepository.getUsersFromJSONPlaceHolder()
    }
}