package com.shuklansh.inspination_jetpackcompose.Fragments

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import coil.compose.AsyncImage


class DetailedFragment : Fragment() {
    private var imglink: String = ""
    private var photographer: String = ""
    private var phtographerurl: String = ""

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
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                        .padding(12.dp)
                ) {

                    //Text(text = "detailed activity")
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.8f),
                        model = imglink,
                        contentDescription = "imageDEtailed"
                    )
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