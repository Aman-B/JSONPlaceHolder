package com.example.jsonplaceholder

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

object RetrofitInstance {
        var BASE_URL = "https://jsonplaceholder.typicode.com/"

         var retroFit : Retrofit? =null

        fun getInstance(): Retrofit? {
                if(retroFit==null)
                {
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