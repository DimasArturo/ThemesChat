package com.example.themeschats

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.themeschats.components.ChatTextField
import com.example.themeschats.components.MessageBox
import com.example.themeschats.enums.Themes
import com.example.themeschats.models.MessageData
import com.example.themeschats.utils.automaticResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(changeTheme: (Themes) -> Unit) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState ,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "Elige el tema de tu agrado",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                HorizontalDivider(
                    color = Color.Gray,
                )
                Box(modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth().clickable {
                        scope.launch {
                            changeTheme(Themes.ORIGINAL)
                            drawerState.close()
                        }
                    }

                ) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF166683))
                        )
                        Text(
                            text = "Original Theme", fontSize = 18.sp,
                        modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
                HorizontalDivider(
                    color = Color.LightGray,
                )
                Box(modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth().clickable {
                        scope.launch {
                            changeTheme(Themes.NATURAL)
                            drawerState.close()
                        }
                    }

                ) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF37693D))
                        )
                        Text(
                            text = "Natural Theme", fontSize = 18.sp,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
                HorizontalDivider(
                    color = Color.LightGray,
                )
                Box(modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth().clickable {
                        scope.launch {
                            drawerState.close()
                        }
                    }

                ) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .background(Color.Red)
                        )
                        Text(
                            text = "Sunset Theme", fontSize = 18.sp,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }

            }
        }){

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(text = "Themes Chat")
                    })
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->


            var listOfMessagesData by rememberSaveable {
                mutableStateOf(listOf<MessageData>() )
            }

            var textInputMessage by rememberSaveable {
                mutableStateOf("")
            }
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                MessageBox(
                    listOfMessagesData=listOfMessagesData,
                    modifier = Modifier.weight(1f),
                    scrollState = scrollState)

                ChatTextField(
                    textInputMessage = textInputMessage,
                    onChangeValueTextInput = { textInputMessage = it },
                    onSendMessage = {
                        val newMessage = MessageData(
                            isMine = true,
                            text = textInputMessage
                        )
                        listOfMessagesData = listOfMessagesData + newMessage
                        textInputMessage = ""

                        val responseMessage = automaticResponse()

                        scope.launch {
                            delay(150)
                            scrollState.animateScrollTo(scrollState.maxValue)

                            delay(2000)
                            listOfMessagesData = listOfMessagesData + responseMessage

                            delay(500)
                            scrollState.animateScrollTo(scrollState.maxValue)

                        }
                    }
                )
            }
        }
    }

}