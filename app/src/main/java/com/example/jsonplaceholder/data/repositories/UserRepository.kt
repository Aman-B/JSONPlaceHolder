package com.example.jsonplaceholder.data.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.jsonplaceholder.data.api.RetrofitInstance
import com.example.jsonplaceholder.data.api.UserEndpoints
import com.example.jsonplaceholder.data.model.PostModel
import com.example.jsonplaceholder.data.model.UserModel
import retrofit2.Call
import retrofit2.Response

class UserRepository {
    private val retroFitInstance = RetrofitInstance.getInstance()?.create(UserEndpoints::class.java)

    fun getUsersFromJSONPlaceHolder(): MutableLiveData<List<UserModel>> {
        val userList: MutableLiveData<List<UserModel>> = MutableLiveData()

        retroFitInstance?.getUserList()?.enqueue(object :
            retrofit2.Callback<List<UserModel>> {
            override fun onResponse(
                call: Call<List<UserModel>>,
                response: Response<List<UserModel>>
            ) {
                Log.i("User Repository :", "" + response.body())
                userList.value = response.body()
            }

            override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
                Log.i("User Repository :", "retrofit call failed :" + t.localizedMessage)
                t.localizedMessage
            }
        })
        return userList
    }

    fun getPostsByUserID(userId: Int): MutableLiveData<List<PostModel>> {
        val postList: MutableLiveData<List<PostModel>> = MutableLiveData()
        retroFitInstance?.getPostsByUserId(userId = userId)?.enqueue(object :
            retrofit2.Callback<List<PostModel>> {
            override fun onResponse(
                call: Call<List<PostModel>>,
                response: Response<List<PostModel>>
            ) {
                Log.i("User Repository :", "" + response.body())
                postList.value = response.body()
            }

            override fun onFailure(call: Call<List<PostModel>>, t: Throwable) {
                Log.i("User Repository :", "retrofit call failed :" + t.localizedMessage)
            }
        })
        return postList
    }

    companion object {
        fun getInstance(): UserRepository {
            return UserRepository()
        }
    }
}