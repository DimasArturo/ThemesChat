package com.example.themeschats.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.themeschats.models.MessageData

@Composable
fun MessageBox(modifier: Modifier,
               listOfMessagesData: List<MessageData>,
               scrollState: ScrollState
){
    //Mensajes
    Column (
        modifier = Modifier.fillMaxWidth().then(modifier).verticalScroll(scrollState)
    ) {

        listOfMessagesData.forEach (){
            MessageCard(
                message = it
            )
        }
    }
}