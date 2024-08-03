package com.example.sql

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TasksListAdapter(var items:List<Tasks> = emptyList()):RecyclerView.Adapter<TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val listItemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return TaskViewHolder(listItemView)
    }

    fun updateItems(itemsToUpdate:List<Tasks>){
        items = itemsToUpdate
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.task.text = items[position].task
    }

}

class TaskViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    val task: TextView = itemView.findViewById(R.id.task)
}
