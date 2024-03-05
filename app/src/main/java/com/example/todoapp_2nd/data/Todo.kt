package com.example.todoapp_2nd.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity // table
data class Todo(
    val title: String,
    val description : String? ,
    val isDone : Boolean,
    @PrimaryKey val id : Int? = null // when creating entity room will generate this id
)
