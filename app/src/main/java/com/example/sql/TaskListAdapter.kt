package com.example.sql

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TasksListAdapter(var items:List<Tasks> = emptyList()):RecyclerView.Adapter<EmployeeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val listItemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return EmployeeViewHolder(listItemView)
    }

    fun updateItems(itemsToUpdate:List<Tasks>){
        items = itemsToUpdate
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.task.text = items[position].task
    }

}

class EmployeeViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    val task: TextView = itemView.findViewById(R.id.task)
}
