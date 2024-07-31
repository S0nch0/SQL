package com.example.sql

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class TasksViewModel: ViewModel() {
    private val repo = MyApplication.getApp().repo
    private val _listState = MutableLiveData<ListState>(ListState.EmptyList)
    val listState: LiveData<ListState> = _listState
    private val observer = Observer<List<Tasks>> {
        _listState.postValue(ListState.UpdatedList(list = it))
    }

    init {
        repo.getAll().observeForever(observer)
    }

    fun addTask(task:String){
        repo.add(Tasks(task = task))
    }

    fun removeTask(task: Tasks){
        repo.remove(task)
    }

    override fun onCleared() {
        repo.getAll().removeObserver(observer)
        super.onCleared()
    }

    sealed class ListState {
        data object EmptyList:ListState()
        data class UpdatedList(val list: List<Tasks>):ListState()
    }
}