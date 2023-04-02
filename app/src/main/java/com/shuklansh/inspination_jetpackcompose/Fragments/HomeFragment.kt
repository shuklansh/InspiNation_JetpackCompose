package com.shuklansh.inspination_jetpackcompose.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.compose.AsyncImage
import com.shuklansh.inspination_jetpackcompose.MainActivity.MainActivityAll
import com.shuklansh.inspination_jetpackcompose.Model.Photo
import com.shuklansh.inspination_jetpackcompose.Model.Src
import com.shuklansh.inspination_jetpackcompose.R
import com.shuklansh.inspination_jetpackcompose.ViewModel.HomeViewModel
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {


    @SuppressLint("UnrememberedMutableState")
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                var loaded by mutableStateOf(false)
                var scope = rememberCoroutineScope()
                var keyboard = LocalSoftwareKeyboardController.current
                var query by remember { mutableStateOf("new york") }
                var listofPhotosResp by rememberSaveable {
                    mutableStateOf(
                        listOf(
                            Photo(
                                0,
                                "",
                                "",
                                Src("", "", "", "")
                            )
                        )
                    )
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black//MaterialTheme.colors.background
                ) {

                    Scaffold(content ={

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                        ) {
                            TextField(
                                trailingIcon = {
                                    IconButton(
                                        content = {
                                            Icon(
                                                Icons.Default.Search,
                                                contentDescription = "submit",
                                                tint = Color.Black
                                            )
                                        },
                                        onClick = {
                                            if (query != "") {
                                                keyboard!!.hide()
                                                scope.launch {
                                                    listofPhotosResp =
                                                        MainActivityAll().getWallpaperList(query)
                                                }
                                                loaded = true
                                            } else {
                                                keyboard!!.hide()
                                                //Toast.makeText(context,"Enter a query", Toast.LENGTH_SHORT)
                                            }
                                        },
                                    )
                                },
                                label = { Text("Search for wallpapers") },
                                modifier = Modifier.fillMaxWidth(),

                                maxLines = 1,
                                colors = TextFieldDefaults.textFieldColors(
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    backgroundColor = Color.Magenta.copy(alpha=0.75f),
                                    cursorColor = Color.Black,
                                    textColor = Color.Black

                                ),
                                shape = RoundedCornerShape(400f),
                                value = query,
                                onValueChange = {
                                    query = it
                                })
                            Spacer(modifier = Modifier.height(20.dp))
                            //                            Column(
                            //                                horizontalAlignment = Alignment.End,
                            //                                modifier = Modifier.fillMaxWidth()
                            //                            ) {
                            //                                Button(
                            //                                    onClick = {
                            //                                        keyboard!!.hide()
                            //                                        scope.launch {
                            //                                            listofPhotosResp = getWallpaperList(query)
                            //                                        }
                            //                                        loaded = true
                            //                                    }) {
                            //                                    Text("Submit")
                            //                                }
                            //                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            LazyColumn(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {

                                items(items = listofPhotosResp) {
                                    if (loaded)
                                        Box(modifier = Modifier.fillMaxSize()) {
                                            AsyncImage(
                                                modifier = Modifier
                                                    .clickable {
                                                        scope.launch {
                                                            val bundle = Bundle()
                                                            bundle.putString(
                                                                "imageURL",
                                                                it.src.large
                                                            )
                                                            bundle.putString(
                                                                "photographer",
                                                                it.photographer
                                                            )
                                                            bundle.putString(
                                                                "photographerurl",
                                                                it.photographer_url
                                                            )
                                                            bundle.putString(
                                                                "id",
                                                                it.id.toString()
                                                            )
                                                            findNavController().navigate(
                                                                R.id.action_homeFragment_to_detailedFragment,
                                                                bundle
                                                            )
                                                        }

                                                    }
                                                    .height(400.dp)
                                                    .clip(
                                                        RoundedCornerShape(50f)
                                                    )
                                                    .drawWithContent {
                                                        drawContent()
                                                        drawRect(
                                                            Brush.verticalGradient(
                                                                listOf(
                                                                    Color.Transparent,
                                                                    Color.Black
                                                                ),
                                                                0f,
                                                                1200f,
                                                            )
                                                        )

                                                    },
                                                contentScale = ContentScale.Crop,
                                                model = it.src.medium,
                                                contentDescription = "image"
                                            )

                                        }


                                }
                            }

                        }
                    }
                    , backgroundColor = Color.Black
                    )


                }
            }
        }
    }

}