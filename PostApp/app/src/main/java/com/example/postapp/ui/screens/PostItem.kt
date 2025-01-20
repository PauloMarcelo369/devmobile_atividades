package com.example.postapp.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.postapp.data.models.Post

@Composable
fun PostItem(
    modifier: Modifier = Modifier,
    post: Post,
    onDelete: (Int) -> Unit,
    onEdit: (Post) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = post.title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = post.content,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Divider(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                thickness = 1.dp,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { showDialog = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = MaterialTheme.colorScheme.onError
                    ),
                    modifier = Modifier.padding(end = 8.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Excluir")
                }

                Button(
                    onClick = { onEdit(post) },
                    modifier = Modifier.padding(start = 8.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Editar")
                }
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = {
                        Text(text = "Confirmar exclusão", style = MaterialTheme.typography.titleMedium)
                    },
                    text = {
                        Text(text = "Tem certeza que deseja excluir este post?")
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                onDelete(post.id)
                                showDialog = false
                            }
                        ) {
                            Text("Sim")
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = { showDialog = false }
                        ) {
                            Text("Não")
                        }
                    }
                )
            }
        }
    }
}
