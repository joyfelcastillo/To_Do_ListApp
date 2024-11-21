package com.jcastillo.listadeproyecto

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jcastillo.listadeproyecto.databinding.ItemTaskBinding

class TaskAdapter(private val tasks: MutableList<Task>) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.binding.apply {
            tvTaskTitle.text = task.title
            cbCompleted.isChecked = task.isCompleted

            // Manejar el clic para eliminar
            btnDelete.setOnClickListener {
                removeTask(position)
            }

            // Manejar clic para editar
            btnEdit.setOnClickListener {
                // Si el EditText está visible, editamos el título y lo ocultamos
                if (etEditTask.visibility == android.view.View.VISIBLE) {
                    val newTitle = etEditTask.text.toString()
                    if (newTitle.isNotBlank()) {
                        editTask(position, newTitle)
                    }
                    etEditTask.visibility = android.view.View.GONE
                    tvTaskTitle.visibility = android.view.View.VISIBLE
                } else {
                    // Si el EditText no está visible, lo mostramos para editar
                    etEditTask.setText(task.title)
                    etEditTask.visibility = android.view.View.VISIBLE
                    tvTaskTitle.visibility = android.view.View.GONE
                }
            }

            cbCompleted.setOnCheckedChangeListener { _, isChecked ->
                task.isCompleted = isChecked
            }
        }
    }

    override fun getItemCount(): Int = tasks.size

    // Métodos para editar y eliminar
    fun removeTask(position: Int) {
        tasks.removeAt(position)
        notifyItemRemoved(position)
    }

    fun editTask(position: Int, newTitle: String) {
        tasks[position].title = newTitle
        notifyItemChanged(position)
    }

    fun addTask(task: Task) {
        tasks.add(task)
        notifyItemInserted(tasks.size - 1)
    }
}
