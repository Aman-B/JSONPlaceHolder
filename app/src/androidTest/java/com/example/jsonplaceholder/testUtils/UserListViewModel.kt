package com.example.jsonplaceholder.testUtils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jsonplaceholder.data.api.UserEndpoints
import com.example.jsonplaceholder.data.models.User

class UserListViewModel(userEndpointInstance: UserEndpoints?) : ViewModel() {
    //private val userRepository = UserRepository.getInstance(userEndpointInstance)
    var userList: MutableLiveData<List<User>> = MutableLiveData()
    /*val getUserList = liveData(Dispatchers.IO) {
       // userList = userRepository.getUsersFromJSONPlaceHolder()
        emitSource(userList)
    }*/

}