package com.example.sql

import android.app.Application
import androidx.room.Room

class MyApplication:Application() {

    lateinit var repo: TasksRepository


    override fun onCreate() {
        super.onCreate()
        instance = this
        val db = Room.databaseBuilder(this, TasksDatabase::class.java, "task_database")
            .build()
        repo = TasksRepository(db)
    }

    companion object {
        private lateinit var instance: MyApplication
        fun getApp() = instance
    }
}


