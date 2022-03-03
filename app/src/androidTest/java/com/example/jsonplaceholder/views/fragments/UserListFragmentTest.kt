package com.example.jsonplaceholder.views.fragments

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onData
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
import org.hamcrest.core.IsAnything.anything
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class UserListFragmentTest {

    @get:Rule
    var scenario: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)
    val targetContext: Context = ApplicationProvider.getApplicationContext()

    //register idling resource so espresso knows that there is a long running task in background.
    @Before
    fun registerIdleResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    //unregister idling resource once the test is done.
    @After
    fun unregisterIdleResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    //Tests if the userList is being displayed or not.
    @Test
    fun userList_isDisplayed() {
        val userListView = onView(withId(R.id.user_list))
        userListView.check(matches(isDisplayed()))
    }

    //Checks if the click on userList item is working or not.
    @Test
    fun userListItem_isClickValid() {
        val userListView = onView(withId(R.id.user_list))
        userListView.check(matches(isDisplayed()))
        onData(anything()).inAdapterView(withId(R.id.user_list)).atPosition(0).perform(click())
        onView((withId(R.id.name))).check(
            matches(
                withText(
                    targetContext.getString(
                        R.string.user_name_text,
                        "Leanne Graham"
                    )
                )
            )
        )
    }
}