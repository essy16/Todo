package com.essycynthia.todo.di

import android.app.Application
import androidx.room.Room
import com.essycynthia.todo.data.ToDoDatabase
import com.essycynthia.todo.data.ToDoRepository
import com.essycynthia.todo.data.ToDoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRoomDatabase(app: Application): ToDoDatabase {
        return Room.databaseBuilder(
            app,
            ToDoDatabase::class.java,
            "todo_db"
        ).build()

    }
    @Provides
    @Singleton
    fun provideTodoRepository(db: ToDoDatabase): ToDoRepository {
        return ToDoRepositoryImpl(db.toDoDao)
    }
}