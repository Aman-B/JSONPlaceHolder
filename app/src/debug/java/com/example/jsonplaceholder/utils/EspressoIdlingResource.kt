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

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {
        if (!countingIdlingResource.isIdleNow) {
            countingIdlingResource.decrement()
        }
    }
}