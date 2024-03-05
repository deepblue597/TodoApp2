package com.example.todoapp_2nd.data


import kotlinx.coroutines.flow.Flow

interface TodoRepo {


    suspend fun insertTodo(todo: Todo) //we suspend until we got the result which means until the insertion finished.
    // it is async programming

    suspend fun deleteTodo(todo: Todo)


    suspend fun getTodoById(id:Int): Todo?


    fun getTodos() : Flow<List<Todo>> // we get real time updates as soon as we have an update.

}