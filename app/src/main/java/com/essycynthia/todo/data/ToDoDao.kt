package com.essycynthia.todo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(toDo: ToDo)

    @Delete
    suspend fun deleteTodo(toDo: ToDo)

    @Query("SELECT * FROM ToDo where id = :id ")
    suspend fun getTodoById(id: Int): ToDo?

    @Query("SELECT * FROM ToDo ")
    fun getAllTodos(): Flow<List<ToDo>>
}