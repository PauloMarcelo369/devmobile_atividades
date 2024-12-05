package com.example.yugiohapp.models

data class Card(
    val id : Int,
    val cardName : String,
    val cardType : String,
    val cardImageRes : Int,
    val cardDescription : String,
    val cardStars : Int,
    var isFavorite : Boolean = false
)

