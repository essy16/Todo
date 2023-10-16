package com.essycynthia.todo.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [Todo::class])
abstract class TodoDatabase : RoomDatabase() {
    abstract val todoDao: TodoDao
}