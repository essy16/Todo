package com.essycynthia.todo.ui.add_edit_todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.essycynthia.todo.data.Todo
import com.essycynthia.todo.data.TodoRepository
import com.essycynthia.todo.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val repository: TodoRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var todo by mutableStateOf<Todo?>(null)
        private set
    var title by mutableStateOf("")
        private set
    var description by mutableStateOf("")
        private set
    var priority by mutableStateOf(0)
    private val _uiEvent = Channel<UiEvent>()

    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val todoId = savedStateHandle.get<Int>("todoId")!!
        if (todoId != -1) {
            viewModelScope.launch {
                repository.getTodoById(todoId)?.let { todo ->
                    title = todo.title
                    description = todo.description ?: ""
                    priority = todo.priority ?: 0
                    this@AddEditViewModel.todo = todo

                }
            }
        }
    }

    fun onEvent(event: AddEditEvent) {
        when (event) {
            is AddEditEvent.OnSaveClick  -> {
               viewModelScope.launch {
                    if (title.isBlank()) {
                        sendEvent(
                            UiEvent.ShowSnackBar(
                                message = "The title cannot be empty"

                            )
                        )
                        return@launch
                    }
                    repository.insertTodo(
                        Todo(
                            id = todo?.id,
                            description = description,
                            title = title,
                            priority = priority,
                            isDone = todo?.isDone ?: false
                        )
                    )
                   sendEvent(UiEvent.PopBackstack)
                }
            }
            is AddEditEvent.OnDescriptionChanged -> {
                description = event.description
            }

            is AddEditEvent.OnTitleChanged -> {
                title = event.title

            }
            is AddEditEvent.OnPriorityUpdated -> {
                priority = event.priority
            }
        }
    }

    private fun sendEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }


}