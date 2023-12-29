package com.essycynthia.todo.ui.add_edit_todo

sealed class AddEditEvent {
    data class OnTitleChanged(val title: String) : AddEditEvent()
    data class OnDescriptionChanged(val description: String) : AddEditEvent()
    object OnSaveClick : AddEditEvent()
    data class OnPriorityUpdated(val priority : Int) : AddEditEvent()
}
