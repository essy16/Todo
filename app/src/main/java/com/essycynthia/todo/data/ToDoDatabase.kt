package com.essycynthia.todo.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [ToDo::class])
abstract class ToDoDatabase : RoomDatabase() {
    abstract val toDoDao: ToDoDao
}