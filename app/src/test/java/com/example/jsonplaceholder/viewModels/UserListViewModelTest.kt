package com.example.jsonplaceholder.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.filters.LargeTest
import com.example.jsonplaceholder.data.api.RetrofitInstance
import com.example.jsonplaceholder.data.api.UserEndpoints
import com.example.jsonplaceholder.data.models.User
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@LargeTest
@RunWith(MockitoJUnitRunner::class)
class UserListViewModelTest {

    /*@Mock
    val userListViewModel : UserListViewModel= mock(UserListViewModel::class.java)
*/
    lateinit var userListViewModel: UserListViewModel

    @Mock
    private lateinit var viewStateObserver: Observer<List<User>>

    @get:Rule
    val userEndpointInstance = RetrofitInstance.getInstance()?.create(UserEndpoints::class.java)


    @Captor
    private lateinit var argumentCaptor: ArgumentCaptor<List<User>>

    var userList: LiveData<List<User>> = MutableLiveData()

    @Before
    fun setUp() {
        userListViewModel = UserListViewModel(userEndpointInstance!!).apply {
            userList.observeForever(viewStateObserver)
        }
    }

    @Test
    fun testUserList() {
        runBlocking {
            userListViewModel.getUserList.observeForever {}
            Mockito.verify(viewStateObserver, Mockito.times(1))
                .onChanged(argumentCaptor.capture())
            val values = argumentCaptor.allValues
            assert(values[0] is List<User>)
        }

    }

}