package com.example.yugiohapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.yugiohapp.models.Card
import com.example.yugiohapp.models.cardList
import com.example.yugiohapp.ui.components.cardItemList

@Composable
fun HomeScreen(onCardSelected : (Card) -> Unit){
    var searchQuery by remember { mutableStateOf("") }
    val filteredCards = remember (searchQuery) {
        cardList.filter { it.cardName.contains(searchQuery, ignoreCase = true) }
    }

    Column (modifier = Modifier.fillMaxSize())  {
        TextField(value = searchQuery, onValueChange = {searchQuery = it},
            label = { Text("Pesquisar") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
        LazyColumn (
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            items(filteredCards) {
                card ->
               cardItemList(card, onCardSelected)
            }
        }
    }
}

//@Composable
//@Preview(showBackground = true)
//fun view(){
//    println(cardList.get(0))
//
//    HomeScreen (){}
//}

