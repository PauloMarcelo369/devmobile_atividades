package com.example.yugiohapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yugiohapp.models.Card
import androidx.compose.material3.Card as compCard

@Composable
fun cardItemList(card : Card, onCardSelected: (Card) -> Unit){
    compCard (modifier = Modifier.fillMaxWidth().padding(8.dp). border(1.dp, Color(0xFF6200EE), shape = RoundedCornerShape(10.dp)), elevation = CardDefaults.cardElevation(4.dp), colors = CardDefaults.cardColors(
        Color.LightGray
    )){
        Column (modifier = Modifier.fillMaxWidth().padding(8.dp)){
            Row (modifier =
                Modifier.
                padding(10.dp)

            ){
                Image(
                    modifier = Modifier.size(130.dp),
                    painter = painterResource(id = card.cardImageRes),
                    contentDescription = "${card.cardName} Image")
                Spacer(modifier = Modifier.width(20.dp))

                Column (
                    modifier =
                    Modifier.width(400.dp).
                    height(130.dp).
                    border(1.dp, Color.Black, shape = RoundedCornerShape(10.dp)),
                    horizontalAlignment = Alignment.CenterHorizontally ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = card.cardName)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(card.cardType,  fontSize = 10.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(card.cardDescription,  fontSize = 10.sp, modifier = Modifier.padding(5.dp))

                    Button(
                        onClick = { onCardSelected(card)},
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text("Mais detalhes", fontSize = 10.sp)
                    }
                }
            }
        }
    }
}

//@Composable
//@Preview(showBackground = true)
//fun view(){
//    cardItemList(card = cardList.get(0)) {}
//}


