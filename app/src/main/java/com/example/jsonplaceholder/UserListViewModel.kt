package com.example.jsonplaceholder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import retrofit2.Call

class UserListViewModel : ViewModel() {

    private val userRespository = UserRespository.getInstance()

    val userList : MutableLiveData<List<UserModel>> = userRespository.getUsersFromJSONPlaceHolder()
}