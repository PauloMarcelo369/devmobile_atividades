package com.example.postapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.postapp.ui.screens.PostScreen
import com.example.postapp.ui.screens.UserScreen

// Classe principal da atividade
class MainActivity  : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen() // Configura a tela principal da aplicação
        }
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter") // Suprime aviso sobre padding padrão não utilizado
@Composable
fun MainScreen() {
    // Estado para controlar qual aba está selecionada (0 para "Usuários", 1 para "Posts")
    var selectedTab by remember { mutableStateOf(0) }

    // Scaffold é um layout que fornece uma estrutura básica para a tela, com barra superior e inferior
    Scaffold(
        // Barra superior com título
        topBar = {
            TopAppBar(
                title = { Text("PostAPP") }, // Título exibido na barra superior
                backgroundColor = MaterialTheme.colorScheme.primary, // Cor de fundo
                contentColor = Color.White // Cor do texto e ícones
            )
        },
        // Barra de navegação inferior
        bottomBar = {
            BottomNavigation {
                // Item de navegação para a tela de usuários
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Usuários") }, // Ícone de pessoa
                    label = { Text("Usuários") }, // Rótulo "Usuários"
                    selected = selectedTab == 0, // Define se este item está selecionado
                    onClick = { selectedTab = 0 } // Altera o estado para a aba de "Usuários"
                )
                // Item de navegação para a tela de posts
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.List, contentDescription = "Posts") }, // Ícone de lista
                    label = { Text("Posts") }, // Rótulo "Posts"
                    selected = selectedTab == 1, // Define se este item está selecionado
                    onClick = { selectedTab = 1 } // Altera o estado para a aba de "Posts"
                )
            }
        }
    ) {
        paddingValues ->
        when (selectedTab) {
            0 -> UserScreen(modifier = Modifier.padding(paddingValues)) // Tela de usuários
            1 -> PostScreen(modifier = Modifier.padding(paddingValues)) // Tela de posts
        }
    }
}