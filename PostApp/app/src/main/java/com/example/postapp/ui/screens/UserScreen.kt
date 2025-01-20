package com.example.postapp.ui.screens



import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.postapp.viewModel.PostViewModel


@Composable
fun UserScreen(
    modifier: Modifier = Modifier,
    viewModel: PostViewModel = viewModel()
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        isLoading = true
        viewModel.fetchUsers()
        isLoading = false
    }

    Column(
        modifier = modifier
            .padding(24.dp)
            .fillMaxWidth()
    ) {
        // Formulário de entrada
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Nome", style = MaterialTheme.typography.h6) },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF0F0F0), shape = RoundedCornerShape(12.dp)),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = "Ícone usuário")
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.primary,
                unfocusedBorderColor = Color.Gray
            ),
            textStyle = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email", style = MaterialTheme.typography.h6) },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF0F0F0), shape = RoundedCornerShape(12.dp)),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "Ícone Email")
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.primary,
                unfocusedBorderColor = Color.Gray
            ),
            textStyle = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(20.dp))

        // Botão para criar usuário
        Button(
            onClick = {
                isLoading = true
                viewModel.createUser(name, email, onSucess = {
                    Toast.makeText(context, "Usuário criado com sucesso!", Toast.LENGTH_SHORT)
                        .show()
                    isLoading = false
                }, onError = {
                    Toast.makeText(context, "Erro ao criar usuário!", Toast.LENGTH_SHORT).show()
                    isLoading = false
                })
                name = ""
                email = ""
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(MaterialTheme.colors.primary, shape = RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = Color.White,
                    strokeWidth = 2.dp
                )
            } else {
                Icon(Icons.Default.Add, contentDescription = "Ícone adicionar", tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Criar Usuário", color = Color.White, style = MaterialTheme.typography.button)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Lista de usuários
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(viewModel.users) { user ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = 12.dp,
                    shape = RoundedCornerShape(20.dp),
                    backgroundColor = Color(0xFFF7F7F7)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth()
                            .background(Color.White, shape = RoundedCornerShape(16.dp))
                    ) {
                        Text(
                            text = "Nome: ${user.name}",
                            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = "Email: ${user.email}",
                            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Normal)
                        )
                    }
                }
            }
        }
    }
}

