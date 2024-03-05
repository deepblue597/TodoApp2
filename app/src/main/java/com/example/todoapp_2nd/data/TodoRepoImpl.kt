package com.example.todoapp_2nd.data

import kotlinx.coroutines.flow.Flow

class TodoRepoImpl(
    private val dao:TodoDao
): TodoRepo {
    override suspend fun insertTodo(todo: Todo) { // these might get more complex when using extensions and apis
        dao.insertTodo(todo)
    }

    override suspend fun deleteTodo(todo: Todo) {
        dao.deleteTodo(todo)
    }

    override suspend fun getTodoById(id: Int): Todo? {
        return dao.getTodoById(id)
    }

    override fun getTodos(): Flow<List<Todo>> {
        return dao.getTodos()
    }
}