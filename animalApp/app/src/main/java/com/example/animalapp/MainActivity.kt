package com.example.animalapp

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.animalapp.atividades.VideoPlayerActivity


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            AnimalAppTheme {
                AnimalApp()
//            }
        }
    }
}

@Composable
fun AnimalApp() {
    val navController = rememberNavController()

    var isDarkOverlayVisible by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                HomeScreen(
                    onAnimalSelected = { animal -> navController.navigate("animal/$animal") },
                    showDarkOverlay = { isDarkOverlayVisible = it }
                )
            }
            composable("animal/{animal}", arguments = listOf(navArgument("animal") { type = NavType.StringType })) { backStackEntry ->
                val animal = backStackEntry.arguments?.getString("animal") ?: "Dog"
                AnimaScreen(animal, showDarkOverlay = { isDarkOverlayVisible = it })
            }
        }

        if (isDarkOverlayVisible) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
            )
        }
    }
}


@Composable
fun AnimalAppMenu(onOptionSelected: (String) -> Unit, showDarkOverlay: (Boolean) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    // Botão de menu
    IconButton(onClick = {
        expanded = true
        showDarkOverlay(true)
    }) {
        Icon(Icons.Default.MoreVert, contentDescription = "Menu")
    }

    // Menu dropdown
    DropdownMenu(
        modifier = Modifier.fillMaxWidth().padding(20.dp).height(100.dp),
        expanded = expanded,

        offset = DpOffset(0.dp, LocalConfiguration.current.screenHeightDp.dp - 100.dp),
        onDismissRequest = {
            expanded = false
            showDarkOverlay(false)
        }
    ) {
        DropdownMenuItem(
            text = { Text("Monkey") },
            onClick = {
                expanded = false
                showDarkOverlay(false)
                onOptionSelected("Monkey")
            }
        )
        DropdownMenuItem(
            text = { Text("Elephant") },
            onClick = {
                expanded = false
                showDarkOverlay(false)
                onOptionSelected("Elephant")
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onAnimalSelected: (String) -> Unit, showDarkOverlay: (Boolean) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Animal App") },
                actions = {
                    AnimalAppMenu(onOptionSelected = onAnimalSelected, showDarkOverlay = showDarkOverlay)
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text("Selecione um Bicho brabo do menu")
        }
    }
}


@Composable
fun AnimaScreen(animal: String, showDarkOverlay: (Boolean) -> Unit) {
    val context = LocalContext.current
    val animalImage = if (animal == "Monkey") R.drawable.macaco else R.drawable.elefante
    val animalSound = if (animal == "Monkey") R.raw.monkey else R.raw.elephant
    val animalVideo = if (animal == "Monkey") R.raw.macaco else R.raw.elefantinho

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = animalImage),
            contentDescription = "$animal Image",
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            try {
                val mediaPlayer = MediaPlayer.create(context, animalSound)
                mediaPlayer?.start()
                mediaPlayer?.setOnCompletionListener {
                    mediaPlayer.release()
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Erro ao inicializar media player, ${e.message} ", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Reproduzir Som")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val intent = Intent(context, VideoPlayerActivity::class.java)
                intent.putExtra("animalVideo", animalVideo)
                context.startActivity(intent)
            }
        ) {
            Text("Reproduzir vídeo")
        }
    }
}
