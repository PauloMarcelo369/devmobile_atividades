package com.example.profileapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //incializando componentes
        val profileImage = findViewById<ImageView>(R.id.profileImage)
        val nameText = findViewById<TextView>(R.id.nameText)
        val descriptionText = findViewById<TextView>(R.id.descriptionText)
        val currentJobText = findViewById<TextView>(R.id.currentJobText)
        val experienceLayout = findViewById<LinearLayout>(R.id.experienceLayout)

        //definindo informações de perfil
        nameText.text = "Ana Silva"
        descriptionText.text = "Desenvolvedora de software com 5 anos de experiência!"
        currentJobText.text = "Emprego Atual: Engeenharia de software da TechX"

        //lista de experiências
        val experiencias = listOf(
            "Analista de sistemas - Empresa A",
            "Desenvolvedora Junior - Empresa B",
            "Estagiária - Empresa C"
        )

        for (experiencia in experiencias){
            val textView = TextView(this)
            textView.text = experiencia
            textView.textSize = 16f
            experienceLayout.addView(textView)
        }


    }
}