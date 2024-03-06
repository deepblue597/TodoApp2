package com.example.todoapp_2nd.ui.theme.todo_list

import com.example.todoapp_2nd.data.Todo

sealed class TodoListEvent {

    data class DeleteTodo(val todo: Todo) : TodoListEvent()
    data class OnDoneChange(val todo: Todo, val isDone: Boolean) : TodoListEvent()
    object OnUndoDeleteClick: TodoListEvent() // it does not need parameters so it is an object
    // if it needed parameters then it wouldnt be an object but a clas
    data class OnTodoCLick(val todo: Todo): TodoListEvent()
    object OnAddTodoClick: TodoListEvent()


}