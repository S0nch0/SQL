package com.example.sql

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.FirebaseDatabase

class ListFragment : Fragment() {
    //private lateinit var viewModel: TasksViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.task_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // viewModel = ViewModelProvider(requireActivity())[TasksViewModel::class.java]
        val listView: RecyclerView = view.findViewById(R.id.listView)
        val fab: FloatingActionButton = view.findViewById(R.id.floatingActionButton)

        listView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = TasksListAdapter()
        listView.adapter = adapter

        val database = FirebaseDatabase.getInstance(
            "https://myfirebase-22a87-default-rtdb.europe-west1.firebasedatabase.app/")
        val target = database.reference
            .child("tasks")

        target.get().addOnCompleteListener { task ->
            val taskList = mutableListOf<Task>()
            if(task.isSuccessful) {
                task.result.children.forEach {
                    val task = it?.getValue(String::class.java)?:""
                    val uuid = it?.key.toString()
                    var taskElement = Task(uuid, task)
                    taskList.add(taskElement)
                }
            }
            adapter.updateItems(taskList)
        }


        //viewModel.listState.observe(viewLifecycleOwner) { uiState ->
        //   when (uiState) {
        //        is TasksViewModel.ListState.EmptyList -> Unit
        //        is TasksViewModel.ListState.UpdatedList -> {
        //            adapter.updateItems(uiState.list)
        //        }
        //    }
        //}
        fab.setOnClickListener {
            val fragment = AddTaskFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(fragment.javaClass.name)
                .commit()
        }
        val itemTouchHelper = ItemTouchHelper (object : ItemTouchHelper.Callback(){
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int = makeMovementFlags(0, ItemTouchHelper.END)
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (direction == ItemTouchHelper.END) {
                //    viewModel.removeTask(adapter.items[viewHolder.adapterPosition])
                    val reference = database.getReference(adapter.items[viewHolder.adapterPosition].uuid)
                    reference.removeValue().addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(requireContext(),"Видалено", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(requireContext(),"Помилка", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        })
        itemTouchHelper.attachToRecyclerView(listView)

    }

    data class Task(val uuid: String, val task: String)
}