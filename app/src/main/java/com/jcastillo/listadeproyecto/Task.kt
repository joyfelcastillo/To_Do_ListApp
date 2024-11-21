package com.jcastillo.listadeproyecto

import com.jcastillo.listadeproyecto.Task

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var title: String,
    var isCompleted: Boolean = false
)
