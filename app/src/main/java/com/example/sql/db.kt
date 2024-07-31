package com.example.sql

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TaskDatabase::class], version = 1)
abstract class TaskDatabase: RoomDatabase(){
    abstract fun taskDAO(): TasksDAO
}