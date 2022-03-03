package com.example.jsonplaceholder.data.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.jsonplaceholder.data.api.UserEndpoints
import com.example.jsonplaceholder.data.models.User
import com.example.jsonplaceholder.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Response

/**
 *
 * User repository class which makes the retorfit calls using enqueue.
 * @param : userEndpoints : the api endpoint/url for users
 */
class UserRepository private constructor(private val userEndpoints: UserEndpoints) {
    private val LOGTAG: String = "UserRepository"

    /**
     * Gets the list of users from remote source : JSONPlaceholder.
     *@return userList : A mutableliveData<List<UserModel>> which contains the list of users
     * received from remote source.
     */
    fun getUsersFromJSONPlaceHolder(): MutableLiveData<List<User>> {
        val userList: MutableLiveData<List<User>> = MutableLiveData()
        EspressoIdlingResource.increment()
        userEndpoints.getUserList().enqueue(object :
            retrofit2.Callback<List<User>> {
            override fun onResponse(
                call: Call<List<User>>,
                response: Response<List<User>>
            ) {
                Log.i(LOGTAG, "response : " + response.body())
                userList.value = response.body()
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.e(LOGTAG, "Retrofit call failed :" + t.localizedMessage)
                EspressoIdlingResource.decrement()
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