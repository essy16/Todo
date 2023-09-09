package com.essycynthia.todo.ui.add_edit_todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.essycynthia.todo.data.ToDo
import com.essycynthia.todo.data.ToDoRepository
import com.essycynthia.todo.ui.add_edit_todo.AddEditEvent.OnSaveClick
import com.essycynthia.todo.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val repository: ToDoRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var todo by mutableStateOf<ToDo?>(null)
        private set
    var title by mutableStateOf("")
        private set
    var description by mutableStateOf("")
        private set

    //It is mutable hence we put it with an underscore
    private val _uiEvent = Channel<UiEvent>()

    //This is the immutable version
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val todoId = savedStateHandle.get<Int>("todoId")!!
        if (todoId != -1) {
            viewModelScope.launch {
                repository.getTodoById(todoId)?.let { todo ->
                    title = todo.title
                    description = todo.description ?: ""
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
                        ToDo(
                            id = todo?.id,
                            description = description,
                            title = title,
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
        }
    }

    private fun sendEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }


}