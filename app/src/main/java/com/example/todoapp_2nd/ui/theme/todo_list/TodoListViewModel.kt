package com.example.todoapp_2nd.ui.theme.todo_list

import androidx.lifecycle.ViewModel
import com.example.todoapp_2nd.data.TodoRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
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

    private val _uiEvent = Channel<UiEvent> {
        TODO("STOPPED IN 36:24")
    }
}