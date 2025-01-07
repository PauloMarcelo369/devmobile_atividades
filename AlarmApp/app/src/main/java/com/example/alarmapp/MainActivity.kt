package com.example.alarmapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.alarmapp.ui.theme.AlarmAppTheme

class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlarmAppTheme {
               Surface(
                   modifier = Modifier.fillMaxSize(),
                   color = androidx.compose.material.MaterialTheme.colors.background
               ) {

                   AlarmScreen(this)
               }

            }
        }
    }
}

