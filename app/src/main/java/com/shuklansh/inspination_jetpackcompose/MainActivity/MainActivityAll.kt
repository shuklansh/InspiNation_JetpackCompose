package com.shuklansh.inspination_jetpackcompose.MainActivity

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.shuklansh.inspination_jetpackcompose.ApiPexels.ApiObjectHelper
import com.shuklansh.inspination_jetpackcompose.ApiPexels.WallpaperApi
import com.shuklansh.inspination_jetpackcompose.Model.Photo
import com.shuklansh.inspination_jetpackcompose.R
import com.shuklansh.inspination_jetpackcompose.ViewModel.HomeViewModel
import java.io.File

class MainActivityAll : AppCompatActivity() {

    var apiCaller = ApiObjectHelper.getInterface().create(WallpaperApi::class.java)

    private lateinit var navcontroller: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main_all)
        var viewmodel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val navhostFrag = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navcontroller = navhostFrag.navController

//        fun DownloadImage(url: String,filename : String) {
//            var dm: DownloadManager? = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
//            val imgURI = Uri.parse(url)
//            var request = DownloadManager.Request(imgURI)
//            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
//                .setMimeType("images/jpeg")
//                .setAllowedOverRoaming(false)
//                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
//                .setTitle(filename)
//                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, File.separator+filename+".jpeg")
//            dm?.enqueue(request)
//            Toast.makeText(this@MainActivityAll,"Downloaded",Toast.LENGTH_SHORT).show()
//        }

    }

//    suspend fun getWallpaperList(query: String): List<Photo> {
//        val result = apiCaller.getSearchResponse(query).photos
//        return result
//    }



}