package com.example.themeschats

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.themeschats.components.ChatTextField
import com.example.themeschats.components.MessageBox
import com.example.themeschats.models.MessageData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
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

        val scope = rememberCoroutineScope()

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

                    scope.launch {
                        delay(100)
                        scrollState.scrollTo(scrollState.maxValue)
                    }
                }
            )
        }
    }
}