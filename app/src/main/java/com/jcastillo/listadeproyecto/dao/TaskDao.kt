package com.jcastillo.listadeproyecto.dao


import androidx.lifecycle.LiveData
import androidx.room.*
import com.jcastillo.listadeproyecto.model.Task

@Dao
interface TaskDao {
    @Insert
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM tasks ORDER BY id DESC")
    fun getAllTasks(): LiveData<List<Task>>
}
