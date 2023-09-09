package com.essycynthia.todo.data

import kotlinx.coroutines.flow.Flow

class ToDoRepositoryImpl(private val dao: ToDoDao) : ToDoRepository {
    override suspend fun insertTodo(toDo: ToDo) {
        dao.insertTodo(toDo)
    }

    override suspend fun deleteTodo(toDo: ToDo) {
        dao.deleteTodo(toDo)
    }

    override suspend fun getTodoById(id: Int): ToDo? {
        return dao.getTodoById(id)
    }

    override fun getAllTodos(): Flow<List<ToDo>> {
        return dao.getAllTodos()
    }
}