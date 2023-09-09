package com.essycynthia.todo.util

sealed class UiEvent {
    object PopBackstack : UiEvent()
    data class Navigate(val route: String) : UiEvent()
    data class ShowSnackBar(val message: String, val action: String? = null) : UiEvent()

}
