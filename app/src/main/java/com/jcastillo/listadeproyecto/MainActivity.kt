package com.jcastillo.listadeproyecto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jcastillo.listadeproyecto.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tasks = mutableListOf<Task>()
        taskAdapter = TaskAdapter(tasks)

        binding.rvTasks.adapter = taskAdapter
        binding.rvTasks.layoutManager = LinearLayoutManager(this)

        binding.btnAddTask.setOnClickListener {
            val taskTitle = binding.etTask.text.toString()
            if (taskTitle.isNotBlank()) {
                val newTask = Task(title = taskTitle)
                taskAdapter.addTask(newTask)
                binding.etTask.text.clear()
            }
        }
    }
}
