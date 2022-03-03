package com.example.jsonplaceholder.viewModels

import com.example.jsonplaceholder.data.models.User

object TestUtils {

    val testUser = User(
        id = 1,
        name = "Leanne Graham",
        username = "Bret",
        email = "Sincere@april.biz", phone = "1-770-736-8031 x56442",
        website = "hildegard.org"
    )
    val testUserData = listOf<User>(testUser)
}