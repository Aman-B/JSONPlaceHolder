package com.example.jsonplaceholder.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.jsonplaceholder.data.api.PostEndpoints
import com.example.jsonplaceholder.data.models.Post
import com.example.jsonplaceholder.data.repositories.PostRepository
import kotlinx.coroutines.Dispatchers

/**
 * View model for UserPostDetailsFragment
 */
class UserPostDetailsViewModel constructor(userID: Int, postEndpoints: PostEndpoints) :
    ViewModel() {

    private val postRepository = PostRepository.getInstance(postEndpoints)

    val getPostList = liveData(Dispatchers.IO) {
        val postList: LiveData<List<Post>>
        postList = postRepository.getPostsByUserID(userID)
        emitSource(postList)
    }

}