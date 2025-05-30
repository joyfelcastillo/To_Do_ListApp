package com.jcastillo.listadeproyecto

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jcastillo.listadeproyecto.adapter.TaskAdapter
import com.jcastillo.listadeproyecto.model.Task
import com.jcastillo.listadeproyecto.viewModel.TaskViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnAddTask: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        recyclerView = findViewById(R.id.recyclerView)
        btnAddTask = findViewById(R.id.btnAddTask)

        setupRecyclerView()

        // Observa os dados do ViewModel e atualiza a lista de tarefas no adapter
        taskViewModel.allTasks.observe(this) { tasks ->
            taskAdapter.updateTasks(tasks)
        }

        // Ação para adicionar uma nova tarefa
        btnAddTask.setOnClickListener {
            showTaskDialog(null)
        }
    }

    private fun setupRecyclerView() {
        taskAdapter = TaskAdapter(
            taskList = emptyList(),
            onEditClick = { task -> showTaskDialog(task) },
            onDeleteClick = { task -> deleteTask(task) }
        )
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = taskAdapter
        }
    }

    private fun showTaskDialog(task: Task?) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_task, null)
        val dialog = AlertDialog.Builder(this)
            .setTitle(if (task == null) getString(R.string.add_task) else getString(R.string.edit_task))
            .setView(dialogView)
            .setPositiveButton(getString(R.string.save)) { _, _ ->
                val title = dialogView.findViewById<EditText>(R.id.etTitle).text.toString()
                val description = dialogView.findViewById<EditText>(R.id.etDescription).text.toString()

                if (title.isNotBlank() && description.isNotBlank()) {
                    if (task == null) {
                        // Agregar nueva tarea
                        taskViewModel.insert(Task(title = title, description = description))
                    } else {
                        // Actualizar tarea existente
                        taskViewModel.update(task.copy(title = title, description = description))
                    }
                } else {
                    Toast.makeText(this, getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton(getString(R.string.cancel), null)
            .create()

        if (task != null) {
            dialogView.findViewById<EditText>(R.id.etTitle).setText(task.title)
            dialogView.findViewById<EditText>(R.id.etDescription).setText(task.description)
        }

        dialog.show()
    }

    private fun deleteTask(task: Task) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.delete_task))
            .setMessage(getString(R.string.confirm_delete_task))
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                taskViewModel.delete(task)
            }
            .setNegativeButton(getString(R.string.no), null)
            .create()
            .show()
    }


}
