package com.example.sql

import java.util.concurrent.Executors

class TasksRepository (private val database: TaskDatabase) {

    private val executor = Executors.newSingleThreadExecutor()

    fun getAll() = database.taskDAO().getAll()

    fun add(task: Tasks){
        executor.execute{ database.taskDAO().add(task) }
    }

    fun remove(task: Tasks){
        executor.execute{ database.taskDAO().delete(task) }
    }
}