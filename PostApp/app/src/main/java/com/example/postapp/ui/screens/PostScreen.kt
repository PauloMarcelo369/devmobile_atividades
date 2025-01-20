    package com.example.postapp.ui.screens
    import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.postapp.data.models.Post
import com.example.postapp.viewModel.PostViewModel


    @Composable
    fun PostScreen(
        modifier: Modifier = Modifier,
        viewModel: PostViewModel = viewModel()
    ) {
        var title by remember { mutableStateOf("") }
        var content by remember { mutableStateOf("") }
        var isLoading by remember { mutableStateOf(false) }
        var editingPost by remember { mutableStateOf<Post?>(null) }
        val context = LocalContext.current

        LaunchedEffect(Unit) {
            isLoading = true
            viewModel.fetchPosts()
            isLoading = false
        }

        Column(
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {

            PostInputFields(
                title = title,
                content = content,
                onTitleChange = { title = it },
                onContentChange = { content = it }
            )

            Spacer(modifier = Modifier.padding(8.dp))

            Button(
                onClick = {
                    isLoading = true
                    viewModel.createPost(
                        title = title,
                        content = content,
                        onSucess = {
                            Toast.makeText(context, "Post criado com sucesso", Toast.LENGTH_SHORT).show()
                            isLoading = false
                        },
                        onError = {
                            Toast.makeText(context, "Erro ao criar post", Toast.LENGTH_SHORT).show()
                            isLoading = false
                        }
                    )
                    title = ""
                    content = ""
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Criar Post")
            }

            Spacer(modifier = Modifier.padding(8.dp))

            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else {
                PostList(
                    posts = viewModel.posts,
                    onEdit = { editingPost = it },
                    onDelete = { viewModel.deletePost(it) }
                )
            }

            if (editingPost != null) {
                EditPostDialog(
                    post = editingPost!!,
                    onDismiss = { editingPost = null },
                    onSave = { updatedPost ->
                        viewModel.updatePost(updatedPost.id, updatedPost.title, updatedPost.content)
                        editingPost = null
                    }
                )
            }
        }
    }

    @Composable
    fun PostInputFields(
        title: String,
        content: String,
        onTitleChange: (String) -> Unit,
        onContentChange: (String) -> Unit
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = onTitleChange,
            label = { Text(text = "Título") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = content,
            onValueChange = onContentChange,
            label = { Text(text = "Conteúdo") },
            modifier = Modifier.fillMaxWidth()
        )
    }

    @Composable
    fun PostList(
        posts: List<Post>,
        onEdit: (Post) -> Unit,
        onDelete: (Int) -> Unit
    ) {
        LazyColumn {
            items(posts) { post ->
                PostItem(
                    post = post,
                    onEdit = onEdit,
                    onDelete = onDelete
                )
            }
        }
    }

    @Composable
    fun EditPostDialog(
        post: Post,
        onDismiss: () -> Unit,
        onSave: (Post) -> Unit
    ) {
        var editedTitle by remember { mutableStateOf(post.title) }
        var editedContent by remember { mutableStateOf(post.content) }

        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(text = "Editar Post") },
            text = {
                Column {
                    OutlinedTextField(
                        value = editedTitle,
                        onValueChange = { editedTitle = it },
                        label = { Text(text = "Título") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    OutlinedTextField(
                        value = editedContent,
                        onValueChange = { editedContent = it },
                        label = { Text(text = "Conteúdo") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onSave(post.copy(title = editedTitle, content = editedContent))
                    }
                ) {
                    Text(text = "Salvar")
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text(text = "Cancelar")
                }
            }
        )
    }
