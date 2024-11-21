package com.example.counterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.counterapp.ui.theme.CounterAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CounterAppTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    CounterApp()
                }
            }
        }
    }
}

@Composable
fun CounterApp() {
    var result by remember { mutableDoubleStateOf(0.0) }
    var input by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center

    ) {
        Text(
            text = "Resultado: ${result}",
            fontSize = 24.sp,
            modifier = Modifier.padding(8.dp)
        )

        OutlinedTextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("Digite um número") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp), // Espaçamento entre os botões
            modifier = Modifier.fillMaxWidth()
        ) {
            // Botão Incrementar
            Button(
                onClick = {
                    result += input.toDoubleOrNull()
                        ?: 0.0 // Adiciona o valor digitado ao resultado
                    input = "" // Limpa o campo de entrada
                },
                modifier = Modifier.weight(1f) // O botão ocupa metade da linha
            ) {
                Text("Incrementar")
            }

            Button(
                onClick = {
                    result -= input.toDoubleOrNull() ?: 0.0 // Subtrai o valor digitado do resultado
                    input = "" // Limpa o campo de entrada
                },
                modifier = Modifier.weight(1f) // O botão ocupa metade da linha
            ) {
                Text("Decrementar")
            }

        }


        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp), // Espaçamento entre os botões
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp) // Espaçamento superior
        ) {
            // Botão Multiplicar
            Button(
                onClick = {
                    result *= input.toDoubleOrNull() ?: 1.0 // Multiplica o resultado pelo valor digitado
                    input = "" // Limpa o campo de entrada
                },
                modifier = Modifier.weight(1f) // O botão ocupa metade da linha
            ) {
                Text("Multiplicar")
            }

            Button(
                onClick = {
                    val value = input.toDoubleOrNull() ?: 1.0
                    if (value != 0.0) { // Verifica se o valor não é zero para evitar divisão por zero
                        result /= value // Divide o resultado pelo valor digitado
                    }
                    input = "" // Limpa o campo de entrada
                },
                modifier = Modifier.weight(1f) // O botão ocupa metade da linha
            ) {
                Text("Dividir")
            }
        }

        Spacer(modifier = Modifier.height(16.dp)) // Espaço antes do botão Limpar

        // Botão Limpar para redefinir o resultado e o campo de entrada
        Button(
            onClick = {
                result = 0.0 // Redefine o resultado para zero
                input = "" // Limpa o campo de entrada
            },
            modifier = Modifier.fillMaxWidth() // O botão ocupa a largura total
        ) {
            Text("Limpar")
        }

    }
}