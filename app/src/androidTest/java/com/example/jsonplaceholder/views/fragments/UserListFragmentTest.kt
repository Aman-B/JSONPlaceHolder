package com.example.jsonplaceholder.views.fragments

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.jsonplaceholder.R
import com.example.jsonplaceholder.views.activities.MainActivity
import org.junit.Rule
import org.junit.Test

class UserListFragmentTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun validateDisplayOfUserNameLists() {
        onView(withId(R.id.user_list)).check(matches(isDisplayed()))
    }
}