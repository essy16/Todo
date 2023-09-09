package com.essycynthia.todo.ui.add_edit_todo

sealed class AddEditEvent {
    data class OnTitleChanged(var title: String) : AddEditEvent()
    data class OnDescriptionChanged(var description: String) : AddEditEvent()
    object OnSaveClick : AddEditEvent()
}
