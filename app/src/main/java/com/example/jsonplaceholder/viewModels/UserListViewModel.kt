package com.example.jsonplaceholder.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.jsonplaceholder.data.api.UserEndpoints
import com.example.jsonplaceholder.data.models.User
import com.example.jsonplaceholder.data.repositories.UserRepository
import kotlinx.coroutines.Dispatchers

/**
 * View model for UserListFragment
 */
class UserListViewModel(userEndpointInstance: UserEndpoints) : ViewModel() {
    private val userRepository = UserRepository.getInstance(userEndpointInstance)
    var userList: LiveData<List<User>> = MutableLiveData()
    val getUserList = liveData(Dispatchers.IO) {
        userList = userRepository.getUsersFromJSONPlaceHolder()
        emitSource(userList)
    }

}