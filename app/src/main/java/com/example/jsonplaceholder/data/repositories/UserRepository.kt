package com.example.jsonplaceholder.data.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.jsonplaceholder.data.api.UserEndpoints
import com.example.jsonplaceholder.data.models.UserModel
import retrofit2.Call
import retrofit2.Response

/**
 * A repository class which requests data from remote data source and returns results.
 */
class UserRepository private constructor(private val userEndpoints: UserEndpoints) {
    private val LOGTAG: String = "UserRepository"

    /**
     * Gets the list of users from remote source : JSONPlaceholder.
     *@return userList : A mutableliveData<List<UserModel>> which contains the list of users
     * received from remote source.
     */
    fun getUsersFromJSONPlaceHolder(): MutableLiveData<List<UserModel>> {
        val userList: MutableLiveData<List<UserModel>> = MutableLiveData()
        userEndpoints.getUserList().enqueue(object :
            retrofit2.Callback<List<UserModel>> {
            override fun onResponse(
                call: Call<List<UserModel>>,
                response: Response<List<UserModel>>
            ) {
                Log.i(LOGTAG, "response : " + response.body())
                userList.value = response.body()
            }

            override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
                Log.i(LOGTAG, "retrofit call failed :" + t.localizedMessage)
                t.localizedMessage
            }
        })
        return userList
    }



    companion object {
        fun getInstance(userEndpoints: UserEndpoints): UserRepository {
            return UserRepository(userEndpoints)
        }
    }
}