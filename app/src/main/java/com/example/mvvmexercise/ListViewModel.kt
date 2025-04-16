package com.example.mvvmexercise

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class taskViewModel : ViewModel() {
//    private var _jobs = mutableStateOf<List<Job>>(emptyList())
//    val jobs: State<List<Job>> = _jobs
//
//    fun addJob(title: String, description: String) {
//        _jobs.value = _jobs.value + Job(title, description)
//    }

    val taskDao = MainApplication.taskDatabase.getTaskDao()
    val taskList : LiveData<List<DataType>> = taskDao.getAllTask()

    fun addTask(title : String, description : String){
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.addTask(DataType(
                title = title, description = description,
            ))
        }
    }

    fun deleteTask(id : Int){
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.deleteTask(id)
        }
    }

    fun deleteAllTask(){
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.deleteAllTask()
        }
    }
}
