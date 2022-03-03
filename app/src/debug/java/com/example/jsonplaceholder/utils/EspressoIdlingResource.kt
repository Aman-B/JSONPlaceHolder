package com.example.jsonplaceholder.utils

import androidx.test.espresso.idling.CountingIdlingResource

/**
 *  EspressoIdlingResource class : This is used for countingIdlingResource.
 *  This is used to notifies espresso that there is a long running task in background.
 */
object EspressoIdlingResource {

    private const val RESOURCE = "Global"

    @JvmField
    val countingIdlingResource = CountingIdlingResource(RESOURCE)

    /**
     * Increment the idling resource counter
     */
    fun increment() {
        countingIdlingResource.increment()
    }

    /**
     * Decrement the idling resource counter, if it is not idle.
     */
    fun decrement() {
        if (!countingIdlingResource.isIdleNow) {
            countingIdlingResource.decrement()
        }
    }
}