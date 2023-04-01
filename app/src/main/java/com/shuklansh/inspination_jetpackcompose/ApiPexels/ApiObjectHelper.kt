package com.shuklansh.inspination_jetpackcompose.ApiPexels

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiObjectHelper {

    val BASE_URL = "https://api.pexels.com/"

    fun getInterface(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}