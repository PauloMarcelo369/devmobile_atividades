package com.example.yugiohapp.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.yugiohapp.models.cardList
import com.example.yugiohapp.ui.screens.CardScreen
import com.example.yugiohapp.ui.screens.HomeScreen

@Composable
@ExperimentalMaterial3Api
fun navGraph(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home"){
            HomeScreen(onCardSelected = {card -> navController.navigate("card/${card.cardName}")})
        }

        composable("card/{card}") {
            navBackStackEntry ->
            val cardName = navBackStackEntry.arguments?.getString("card")
            val selectedCard = cardList.first {it.cardName == cardName}
            CardScreen(selectedCard)
        }
    }

}