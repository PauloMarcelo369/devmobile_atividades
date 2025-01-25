package com.example.msgapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.msgapp.data.local.database.AppDataBase
import com.example.msgapp.repository.MessageRepository
import com.example.msgapp.ui.theme.MsgAppTheme
import com.example.msgapp.ui.view.MessageApp
import com.example.msgapp.viewModel.MessageViewModel
import com.example.msgapp.viewModel.MessageViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "messages-db"
        ).fallbackToDestructiveMigration().build()
        val repository = MessageRepository(db.messageDao())

        setContent {
            MsgAppTheme {
                val viewModel: MessageViewModel = viewModel(factory = MessageViewModelFactory(repository))
                MessageApp(viewModel) // Certifique-se de que esta chamada é única
            }
        }
    }
}


