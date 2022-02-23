package com.example.jsonplaceholder

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Response

class UserRespository constructor() {
    val retrofit = RetrofitInstance.getInstance()?.create(UserEndpoints::class.java)


    fun getUsersFromJSONPlaceHolder(): MutableLiveData<List<UserModel>> {
        val userList: MutableLiveData<List<UserModel>> = MutableLiveData()

        retrofit?.getUserList()?.enqueue(object :
            retrofit2.Callback<List<UserModel>> {
            override fun onResponse(
                call: Call<List<UserModel>>,
                response: Response<List<UserModel>>
            ) {
                Log.i("amanTag", "" + response.body())
                userList.value = response.body()
            }

            override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
                Log.i("amanTag", " failed :" + t.localizedMessage)
                t.localizedMessage
            }

        })
        return userList
    }

    fun getPostsByUserID(userId: Int): MutableLiveData<List<PostModel>> {
        val postList: MutableLiveData<List<PostModel>> = MutableLiveData()

        retrofit?.getPostsByUserId(userId = userId)?.enqueue(object :
            retrofit2.Callback<List<PostModel>> {
            override fun onResponse(
                call: Call<List<PostModel>>,
                response: Response<List<PostModel>>
            ) {
                Log.i("amanTag", "" + response.body())
                postList.value = response.body()
            }

            override fun onFailure(call: Call<List<PostModel>>, t: Throwable) {
                Log.i("amanTag", " failed :" + t.localizedMessage)
            }

        })
        return postList
    }

    companion object {
        fun getInstance(): UserRespository {
            return UserRespository()
        }
    }
}