package com.example.jsonplaceholder.data.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.jsonplaceholder.data.api.PostEndpoints
import com.example.jsonplaceholder.data.models.Post
import com.example.jsonplaceholder.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Response

class PostRepository private constructor(private val postEndpoints: PostEndpoints) {

    private val LOGTAG: String = "PostRepository"

    /**
     * Gets the list of posts for a specific user by using their userID.
     * @param userID : userId of the User
     * @return postList : List of user's posts with post details received from remote source.
     */
    fun getPostsByUserID(userID: Int): MutableLiveData<List<Post>> {
        val postList: MutableLiveData<List<Post>> = MutableLiveData()
        EspressoIdlingResource.increment()
        postEndpoints.getPostsByUserId(userId = userID).enqueue(object :
            retrofit2.Callback<List<Post>> {
            override fun onResponse(
                call: Call<List<Post>>,
                response: Response<List<Post>>
            ) {
                Log.i(LOGTAG, "" + response.body())
                postList.value = response.body()
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.e(LOGTAG, "Retrofit call failed :" + t.localizedMessage)
                EspressoIdlingResource.decrement()
            }
        })
        return postList
    }

    companion object {
        fun getInstance(postEndpoints: PostEndpoints): PostRepository {
            return PostRepository(postEndpoints)
        }
    }
}