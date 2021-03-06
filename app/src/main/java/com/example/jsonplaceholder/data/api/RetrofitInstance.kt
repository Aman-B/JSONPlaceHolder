package com.example.jsonplaceholder.data.api

import com.example.jsonplaceholder.utils.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Returns an instance of retrofit
 */
object RetrofitInstance {
    private var retroFit: Retrofit? = null
    fun getInstance(): Retrofit? {
        if (retroFit == null) {
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            retroFit = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(BASE_URL)
                .build()
        }
        return retroFit
    }
}