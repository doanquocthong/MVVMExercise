package com.example.mvvmexercise

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class JobViewModel : ViewModel() {
    private var _jobs = mutableStateOf<List<Job>>(emptyList())
    val jobs: State<List<Job>> = _jobs

    fun addJob(title: String, description: String) {
        _jobs.value = _jobs.value + Job(title, description)
    }
}