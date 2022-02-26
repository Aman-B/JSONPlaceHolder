package com.example.jsonplaceholder.data.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.jsonplaceholder.data.api.PostEndpoints
import com.example.jsonplaceholder.data.models.PostModel
import retrofit2.Call
import retrofit2.Response

class PostRepository private constructor(private val postEndpoints: PostEndpoints) {

    private val LOGTAG: String = "PostRepository"

    /**
     * Gets the list of posts for a specific user by using their userID.
     * @param userID : userId of the User
     * @return postList : List of user's posts with post details received from remote source.
     */
    fun getPostsByUserID(userID: Int): MutableLiveData<List<PostModel>> {
        val postList: MutableLiveData<List<PostModel>> = MutableLiveData()
        postEndpoints.getPostsByUserId(userId = userID).enqueue(object :
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
        fun getInstance(postEndpoints: PostEndpoints): PostRepository {
            return PostRepository(postEndpoints)
        }
    }
}