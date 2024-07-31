package com.example.sql

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity
data class Tasks(@PrimaryKey(autoGenerate = true) val id:Int?=null, val task:String)

@Dao
interface TasksDAO{
    @Insert
    fun add(task: Tasks)

    @Delete
    fun delete(task: Tasks)

    @Query("SELECT * FROM tasks")
    fun getAll():LiveData<List<Tasks>>
}