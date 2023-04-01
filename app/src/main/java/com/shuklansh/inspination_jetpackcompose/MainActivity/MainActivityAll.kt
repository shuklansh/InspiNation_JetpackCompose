package com.shuklansh.inspination_jetpackcompose.MainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.shuklansh.inspination_jetpackcompose.ApiPexels.ApiObjectHelper
import com.shuklansh.inspination_jetpackcompose.ApiPexels.WallpaperApi
import com.shuklansh.inspination_jetpackcompose.Model.Photo
import com.shuklansh.inspination_jetpackcompose.R
import com.shuklansh.inspination_jetpackcompose.ViewModel.HomeViewModel

class MainActivityAll : AppCompatActivity() {

    var apiCaller = ApiObjectHelper.getInterface().create(WallpaperApi::class.java)

    private lateinit var navcontroller : NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main_all)
        val navhostFrag = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navcontroller = navhostFrag.navController
    }

    suspend fun getWallpaperList(query: String): List<Photo> {
        val result = apiCaller.getSearchResponse(query).photos
        return result
    }
}