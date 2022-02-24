package com.example.jsonplaceholder.data.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.jsonplaceholder.data.api.RetrofitInstance
import com.example.jsonplaceholder.data.api.UserEndpoints
import com.example.jsonplaceholder.data.models.PostModel
import com.example.jsonplaceholder.data.models.UserModel
import retrofit2.Call
import retrofit2.Response

/**
 * A repository class which requests data from remote data source and returns results.
 */
class UserRepository {
    private val LOGTAG: String = "UserRepository"
    private val retroFitInstance = RetrofitInstance.getInstance()?.create(UserEndpoints::class.java)

    /**
     * Gets the list of users from remote source : JSONPlaceholder.
     *@return userList : A mutableliveData<List<UserModel>> which contains the list of users
     * received from remote source.
     */
    fun getUsersFromJSONPlaceHolder(): MutableLiveData<List<UserModel>> {
        val userList: MutableLiveData<List<UserModel>> = MutableLiveData()
        retroFitInstance?.getUserList()?.enqueue(object :
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

    /**
     * Gets the list of posts for a specific user by using their userID.
     * @param userID : userId of the User
     * @return postList : List of user's posts with post details received from remote source.
     */
    fun getPostsByUserID(userID: Int): MutableLiveData<List<PostModel>> {
        val postList: MutableLiveData<List<PostModel>> = MutableLiveData()
        retroFitInstance?.getPostsByUserId(userId = userID)?.enqueue(object :
            retrofit2.Callback<List<PostModel>> {
            override fun onResponse(
                call: Call<List<PostModel>>,
                response: Response<List<PostModel>>
            ) {
                Log.i(LOGTAG, "" + response.body())
                postList.value = response.body()
            }

            override fun onFailure(call: Call<List<PostModel>>, t: Throwable) {
                Log.i(LOGTAG, "retrofit call failed :" + t.localizedMessage)
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