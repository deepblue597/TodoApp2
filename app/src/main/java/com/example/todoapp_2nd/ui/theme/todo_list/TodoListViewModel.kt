package com.example.todoapp_2nd.ui.theme.todo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp_2nd.data.Todo
import com.example.todoapp_2nd.data.TodoRepo
import com.example.todoapp_2nd.util.Routes
import com.example.todoapp_2nd.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private  val repo : TodoRepo

) : ViewModel() {

    val todos = repo.getTodos()

    // one time event : a thing that happens once.
    // these dont want to assign new state.
    // the state will kept on screen rotations.
    // we dont want to see the snackbar again
    // also the click button want to happen once

    private val _uiEvent = Channel<UiEvent> ()
    val uiEvent = _uiEvent.receiveAsFlow() // the ui should do something when click a button

    private var deletedTodo: Todo? = null

    fun OnEvenet(event: TodoListEvent) {
        when(event)
        {
            is TodoListEvent.OnTodoCLick -> {

                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO + "?todoId=${event.todo.id}"))

            }
            is TodoListEvent.OnAddTodoClick -> {
                // we want to navigate to add to do list
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO))


            }
            is TodoListEvent.OnUndoDeleteClick -> {

                deletedTodo?.let { todo -> {
                    viewModelScope.launch {
                        repo.insertTodo(todo)

                    }
                }


                }

            }
            is TodoListEvent.DeleteTodo -> {

                viewModelScope.launch {
                    deletedTodo = event.todo
                    repo.deleteTodo(
                        event.todo
                    )
                    sendUiEvent((
                            UiEvent.ShowSnackBar(
                                message = "Todo deleted",
                                action = "Undo"
                            )
                            ))
                }

            }
            is TodoListEvent.OnDoneChange -> {

                viewModelScope.launch {
                    repo.insertTodo(
                        event.todo.copy(
                            isDone =  event.isDone
                        )
                    )
                }

            }
        }

    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}