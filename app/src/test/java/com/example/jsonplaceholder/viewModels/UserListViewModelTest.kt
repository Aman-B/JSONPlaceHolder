package com.example.jsonplaceholder.viewModels


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.jsonplaceholder.data.api.RetrofitInstance
import com.example.jsonplaceholder.data.api.UserEndpoints
import com.example.jsonplaceholder.data.models.User
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class UserListViewModelTest {

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    lateinit var userListViewModel: UserListViewModel

    val userEndpointInstance = RetrofitInstance.getInstance()?.create(UserEndpoints::class.java)

    var fetchedUserList: List<User> = mutableListOf()

    @Before
    fun setUp() {
        userListViewModel = UserListViewModel(userEndpointInstance!!)

    }

    //Not working
    @Test
    fun testUserList() = runBlocking {

        userListViewModel.getUserList.observeForever { fetchedUserList = it }
        System.out.println(fetchedUserList)
        assertThat(userListViewModel.userList, `is`(TestUtils.testUserData))
    }

}