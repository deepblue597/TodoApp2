package com.example.todoapp_2nd.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// data access object
// it is a class to access our data
// how to retrieve and change our data
// the functions to interact with the data
@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) // if they have the same ID then it will be replaced
    suspend fun insertTodo(todo: Todo) //we suspend until we got the result which means until the insertion finished.
    // it is async programming

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query("SELECT * FROM Todo WHERE id= :id")
    suspend fun getTodoById(id:Int): Todo?

    @Query("SELECT * FROM Todo")
    fun getTodos() : Flow<List<Todo>> // we get real time updates as soon as we have an update.


}