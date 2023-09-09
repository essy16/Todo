package com.essycynthia.todo.ui.todolist

import com.essycynthia.todo.data.ToDo

sealed class TodolistEvent {
    data class DeleteTodo(val todo: ToDo) : TodolistEvent()
    data class OnDoneChanged(val todo: ToDo, val isDone: Boolean) : TodolistEvent()
    object UndoDelete : TodolistEvent()
    data class OnClickTodo(val todo: ToDo) : TodolistEvent()
    object OnAddTodo : TodolistEvent()
}
