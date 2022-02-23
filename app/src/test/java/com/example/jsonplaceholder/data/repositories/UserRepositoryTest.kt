package com.example.jsonplaceholder.data.repositories

import androidx.lifecycle.MutableLiveData
import com.example.jsonplaceholder.data.models.PostModel
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Test

class UserRepositoryTest {

    @Test
    fun getPostsByUserID() {
        //testing for edge case of userId = 11. As the api only has 10 users.
        val postList = MutableLiveData<List<PostModel>>()
        val userId = 11
        val result = UserRepository.getInstance().getPostsByUserID(userId)
        MatcherAssert.assertThat(result.value, `is`(postList.value))
    }
}