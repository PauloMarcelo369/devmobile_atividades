package com.example.yugiohapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.yugiohapp.models.Card
import com.example.yugiohapp.models.cardList


@ExperimentalMaterial3Api
@Composable
fun CardScreen(card : Card){
    Scaffold (
        topBar = {
            TopAppBar(title = { Text(card.cardName) })
        }
    ) {
        paddingValues ->
         Column(
             modifier =
             Modifier
                 .fillMaxSize()
                 .padding(paddingValues)
                 .padding(16.dp),
             horizontalAlignment = Alignment.CenterHorizontally
         ) {
             Image(
                 painter = painterResource(id = card.cardImageRes),
                 contentDescription = "${card.cardName}",
                 modifier = Modifier.size(200.dp)
             )
             Spacer(modifier = Modifier.height(16.dp))
             Text(
                 text = card.cardType,
                 style = MaterialTheme.typography.titleMedium
             )
             Spacer(modifier = Modifier.height(16.dp))
             Text(
                 text = card.cardDescription,
                 style = MaterialTheme.typography.titleMedium
             )
             Spacer(modifier = Modifier.height(16.dp))
             Text(
                 text = "stars: ${card.cardStars}",
                 style =  MaterialTheme.typography.bodySmall,
                 color = MaterialTheme.colorScheme.secondary
             )
         }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun viw(){
    CardScreen(cardList[0])
}
