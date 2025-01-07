package com.example.nighteventsapp2.ui.theme

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Event(
    val id : Int,
    val title : String,
    val description : String,
    val date : String,
    val location : String,
    val isFavorite : MutableState<Boolean> = mutableStateOf(false),
    val isSubscribed : MutableState<Boolean> = mutableStateOf(false),
    val imageRes : Int
)

val eventList = listOf(
    Event(
        id = 1,
        title = "Conferência de Tecnologia 2024",
        description = "Tendências em Tecnologia.",
        date = "2024-12-15",
        location = "Parque Tecnológico",
        imageRes = 1
    )
)