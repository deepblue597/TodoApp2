package com.example.todoapp_2nd.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapp_2nd.data.TodoDatabase
import com.example.todoapp_2nd.data.TodoRepo
import com.example.todoapp_2nd.data.TodoRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// define the dependencies and their lifetime

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(app:Application): TodoDatabase{
        return Room.databaseBuilder(
            app,
            TodoDatabase::class.java ,
            "todo_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepo(db: TodoDatabase): TodoRepo {
        return TodoRepoImpl(db.dao)
    }
}