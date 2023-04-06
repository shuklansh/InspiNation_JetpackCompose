package com.shuklansh.inspination_jetpackcompose.Fragments

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import coil.compose.AsyncImage
import com.shuklansh.inspination_jetpackcompose.MainActivity.MainActivityAll
import com.shuklansh.inspination_jetpackcompose.R
import java.io.File


class DetailedFragment : Fragment() {
    private var imglink: String = ""
    private var photographer: String = ""
    private var idimg: String = ""
    private var phtographerurl: String = ""

    fun DownloadImage(url: String, filename: String) {
        var dm: DownloadManager? =
            activity?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val imgURI = Uri.parse(url)
        var request = DownloadManager.Request(imgURI)
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            .setMimeType("images/jpeg")
            .setAllowedOverRoaming(false)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle(filename)
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                File.separator + filename + ".jpg"
            )
        dm?.enqueue(request)
        Toast.makeText(activity as Context, "Downloaded", Toast.LENGTH_SHORT).show()
    }

    //private var imglink : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString("imageURL")?.let {
            imglink = it
        }
        arguments?.getString("photographer")?.let {
            photographer = it
        }
        arguments?.getString("photographerurl")?.let {
            phtographerurl = it
        }
        arguments?.getString("id")?.let {
            idimg = it
        }
    }

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return layoutInflater.inflate(R.layout.fragment_detailed,container,false).apply {

            findViewById<ComposeView>(R.id.fragDetailedText).setContent {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                        .padding(12.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    IconButton(onClick = {
                        findNavController().popBackStack()
                        //findNavController().navigate(R.id.action_detailedFragment_to_homeFragment)

                        //findNavController().popBackStack(R.id.homeFragment,false,true)
                        //findNavController().navigate(R.id.action_detailedFragment_to_homeFragment)

                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "backIcon",
                            tint = Color.Magenta
                        )
                    }
                    AsyncImage(
                        modifier = Modifier.height(600.dp),
                        model = imglink,
                        contentScale = ContentScale.FillBounds,
                        contentDescription = "imageDEtailed"
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        modifier = Modifier,
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
                        border = BorderStroke(1.dp, Color.Magenta),
                        shape = RoundedCornerShape(500f), onClick = {
                            DownloadImage(imglink, idimg)
                        }) {
                        Text("Download", color = Color.Magenta, fontSize = 15.sp)
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = photographer,
                        color = Color.White,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        modifier = Modifier.clickable {
//                            WebView(activity as Context).apply {
//                                webViewClient = WebViewClient()
//                                loadUrl(phtographerurl)
//                            }

                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(phtographerurl))
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            intent.setPackage("com.android.chrome")
                            try {
                                context.startActivity(intent)
                                activity?.finish()
                            } catch (ex: ActivityNotFoundException) {
                                // Chrome browser presumably not installed so allow user to choose instead
                                intent.setPackage(null)
                                context.startActivity(intent)
                            }


                        },
                        text = phtographerurl,
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.W500
                    )


                }
            }
        }
    }

}


//@Composable
//fun loadWebUrl(url: String) {
//
//    AndroidView(factory = {
//        WebView(MainActivityAll().applicationContext).apply {
//            webViewClient = WebViewClient()
//
//            loadUrl(url)
//        }
//    })
//}