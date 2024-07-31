package com.example.sql

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class AddTaskFragment : Fragment() {
    private lateinit var viewModel:TasksViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.enter_task_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(requireActivity()).get(TasksViewModel::class.java)
        val taskInputField: EditText = view.findViewById(R.id.editText)
        val addButton: Button = view.findViewById(R.id.button)
        addButton.setOnClickListener {
            val task = taskInputField.text.toString()
            viewModel.addTask(task)
            parentFragmentManager.popBackStack()
        }
    }
}