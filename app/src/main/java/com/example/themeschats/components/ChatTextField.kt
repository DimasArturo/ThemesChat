package com.example.themeschats.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ChatTextField(
    textInputMessage: String,
    onChangeValueTextInput: (String) -> Unit,
    onSendMessage: () -> Unit
) {
    //TextField
    Box(
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ){

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = MaterialTheme.shapes.large
                ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ),
            trailingIcon ={
                IconButton(
                    enabled = textInputMessage.isNotEmpty(),
                    onClick = {
                        onSendMessage()
                    }) {
                    Icon(imageVector = Icons.AutoMirrored.Default.Send, contentDescription = "Send Message")
                }
            },
            value = textInputMessage, onValueChange = {
                onChangeValueTextInput(it)
            })

    }
}