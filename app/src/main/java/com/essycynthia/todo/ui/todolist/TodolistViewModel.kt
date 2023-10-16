package com.essycynthia.todo.ui.todolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.essycynthia.todo.data.Todo
import com.essycynthia.todo.data.TodoRepository
import com.essycynthia.todo.util.Routes
import com.essycynthia.todo.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodolistViewModel @Inject constructor(private val repository: TodoRepository) : ViewModel() {
    val todos = repository.getAllTodos()

    //It is mutable hence we put it with an underscore
    private val _uiEvent = Channel<UiEvent>()

    //This is the immutable version
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedTodo: Todo? = null

    fun onEvent(event: TodolistEvent) {
        when (event) {
            is TodolistEvent.DeleteTodo -> {
                viewModelScope.launch {
                    deletedTodo = event.todo
                    repository.deleteTodo(event.todo)
                    UiEvent.ShowSnackBar("Todo deleted", "Undo")
                }

            }
            is TodolistEvent.OnDoneChanged -> {
                viewModelScope.launch {
                    repository.insertTodo(
                        event.todo.copy(
                            isDone = event.isDone
                        )
                    )
                }

            }
            is TodolistEvent.UndoDelete -> {
                deletedTodo?.let {
                    viewModelScope.launch {
                        repository.insertTodo(it)

                    }

                }

            }
            is TodolistEvent.OnAddTodo -> {
                sendEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO))
            }
            is TodolistEvent.OnClickTodo -> {
                sendEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO + "?todoId=${event.todo.id}"))


            }


        }
    }

    private fun sendEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}