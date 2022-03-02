package com.example.jsonplaceholder.utils

import androidx.test.espresso.idling.CountingIdlingResource

/**
 * Dummy EspressoIdlingResource class as we don't want this to be in the release build.
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