package com.example.themeschats.utils

import com.example.themeschats.models.MessageData


val listOfResponses = listOf<String>(
    "Hola",
    "Que accion, que ondas, que pachuca, que uvas!",
    "buenos dias",
    "Adios",
    "me parece perfecto hijodeputa",
    "tutamadre",
    "un gusto",
    "No me digas jaja",
    "No me gusta",
    "Eso esta muy rico",
)

fun automaticResponse() : MessageData {
    val randomResponse = listOfResponses.random()
    val response = MessageData(
        isMine = false,
        text = randomResponse
    )
    return response
}