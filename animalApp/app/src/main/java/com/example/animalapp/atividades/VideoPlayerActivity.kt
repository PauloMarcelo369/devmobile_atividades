package com.example.animalapp.atividades

import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.animalapp.R

class VideoPlayerActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            val animalVideo = intent.getIntExtra("animalVideo", R.raw.macaco)
            AndroidView(
                factory = {context -> VideoView(context).apply {
                    setVideoURI(Uri.parse("android.resource://$packageName/$animalVideo"))
                    start()
                }}, modifier = Modifier.fillMaxSize()
            )
        }
    }
}