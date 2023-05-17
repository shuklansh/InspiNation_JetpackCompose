package com.shuklansh.inspination_jetpackcompose.ApiPexels

import com.shuklansh.inspination_jetpackcompose.Model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WallpaperApi {

    @Headers("Authorization: auth key")
    @GET("v1/search")
    suspend fun getSearchResponse(
        @Query("query") query: String,
        @Query("page") page : Int = 1,
        @Query("per_page") per_page : Int = 50
    ): SearchResponse

}