package com.essycynthia.todo.ui.todolist

import com.essycynthia.todo.data.Todo

sealed class TodolistEvent {
    data class DeleteTodo(val todo: Todo) : TodolistEvent()
    data class OnDoneChanged(val todo: Todo, val isDone: Boolean) : TodolistEvent()
    object UndoDelete : TodolistEvent()
    data class OnClickTodo(val todo: Todo) : TodolistEvent()
    object OnAddTodo : TodolistEvent()
}
