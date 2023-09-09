package com.essycynthia.todo.data

import kotlinx.coroutines.flow.Flow

interface ToDoRepository {

    suspend fun insertTodo(toDo: ToDo)

    suspend fun deleteTodo(toDo: ToDo)

    suspend fun getTodoById(id: Int): ToDo?

    fun getAllTodos(): Flow<List<ToDo>>
}