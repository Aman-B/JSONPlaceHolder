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

    //get Users from JSONPlaceHolder remote source.
    var userList: MutableLiveData<List<UserModel>> = MutableLiveData<List<UserModel>>()

    fun getUsersFromReposistory(): LiveData<List<UserModel>> {
        return userList
    }

    init {
        userList = userRepository.getUsersFromJSONPlaceHolder()
    }
}