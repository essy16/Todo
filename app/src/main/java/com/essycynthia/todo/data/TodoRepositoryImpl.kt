package com.essycynthia.todo.data

import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(private val dao: TodoDao) : TodoRepository {
    override suspend fun insertTodo(toDo: Todo) {
        dao.insertTodo(toDo)
    }

    override suspend fun deleteTodo(toDo: Todo) {
        dao.deleteTodo(toDo)
    }

    override suspend fun getTodoById(id: Int): Todo? {
        return dao.getTodoById(id)
    }

    override fun getAllTodos(): Flow<List<Todo>> {
        return dao.getAllTodos()
    }
}