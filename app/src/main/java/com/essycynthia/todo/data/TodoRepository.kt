package com.essycynthia.todo.data

import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    suspend fun insertTodo(toDo: Todo)

    suspend fun deleteTodo(toDo: Todo)

    suspend fun getTodoById(id: Int): Todo?

    fun getAllTodos(): Flow<List<Todo>>
}