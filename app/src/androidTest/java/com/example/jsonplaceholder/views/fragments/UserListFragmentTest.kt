package com.example.jsonplaceholder.views.fragments

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.jsonplaceholder.R
import com.example.jsonplaceholder.utils.EspressoIdlingResource
import com.example.jsonplaceholder.views.activities.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class UserListFragmentTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var scenario: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)


    @Before
    fun registerIdleResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisterIdleResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)

    }

    @Test
    fun userList_isDisplayed() {
        //Thread.sleep(10000)

        val userListView = onView(withId(R.id.user_list))
        userListView.check(matches(isDisplayed()))
    }

    @Test
    fun userListItem_isClickValid() {
        // Thread.sleep(12000)

        val userListView = onView(withId(R.id.user_list))

        userListView.check(matches(isDisplayed()))

        onView((withText("Leanne Graham"))).check(matches(isDisplayed())).perform(click())

    }


}