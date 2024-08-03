package com.example.sql

import java.util.concurrent.Executors

class TasksRepository (private val database: TasksDatabase) {

    private val executor = Executors.newSingleThreadExecutor()

    fun getAll() = database.tasksDao().getAll()

    fun add(task: Tasks){
        executor.execute{ database.tasksDao().add(task) }
    }

    fun remove(task: Tasks){
        executor.execute{ database.tasksDao().delete(task) }
    }
}