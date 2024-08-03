package com.example.sql

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Tasks::class], version = 1)
abstract class TasksDatabase : RoomDatabase() {
    abstract fun tasksDao(): TasksDAO
}