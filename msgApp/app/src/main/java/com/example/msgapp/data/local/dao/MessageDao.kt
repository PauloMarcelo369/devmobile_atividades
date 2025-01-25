package com.example.msgapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.msgapp.model.Message

@Dao
interface MessageDao {
    @Query("SELECT * FROM messages ORDER BY timestamp DESC")
    fun getAllMessages() : kotlinx.coroutines.flow.Flow<List<Message>>

    @Insert
    suspend fun insertMessage(message: Message)
}