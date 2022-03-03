package com.example.jsonplaceholder.views.fragments


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
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
class UserPostDetailsFragmentTest {

    @get:Rule
    var scenario: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    var userName = "Ervin Howell"

    //Register idling resource so espresso knows that there is a long running task in background.
    @Before
    fun registerIdleResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        val userListView = onView(ViewMatchers.withId(R.id.user_list))
        userListView.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView((ViewMatchers.withText(userName))).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(
                ViewActions.click()
            )
    }

    //Unregister idling resource once the test is done.
    @After
    fun unregisterIdleResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }


    //Tests if the user_name on UserPostDetailsFragment is displayed or not.
    @Test
    fun userDetails_isDisplayed() {

        val userNameView = onView(ViewMatchers.withId(R.id.user_name))
        userNameView.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    //Tests if the posts on UserPostDetailsFragment are displayed or not.
    @Test
    fun postDetails_isDisplayed() {
        val postDetailsRecyclerView = onView(ViewMatchers.withId(R.id.post_details_recyclerView))
        postDetailsRecyclerView.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(ViewMatchers.withText("PostID : 12"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }
}