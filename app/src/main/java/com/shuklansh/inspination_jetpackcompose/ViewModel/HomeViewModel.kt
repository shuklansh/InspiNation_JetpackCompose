package com.shuklansh.inspination_jetpackcompose.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shuklansh.inspination_jetpackcompose.ApiPexels.ApiObjectHelper
import com.shuklansh.inspination_jetpackcompose.ApiPexels.WallpaperApi
import com.shuklansh.inspination_jetpackcompose.Model.Photo
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    var apiCaller = ApiObjectHelper.getInterface().create(WallpaperApi::class.java)
    var isLoading = mutableStateOf(false)
    var listofPhotos = listOf<Photo>()

    init {
        loadData("new york")
    }

    fun loadData(query : String){
        viewModelScope.launch {
            isLoading.value = true
            listofPhotos = getWallpaperList(query)
            isLoading.value=false

        }
    }

    suspend fun getWallpaperList(query: String): List<Photo> {
        val result = apiCaller.getSearchResponse(query).photos
        return result
    }

}