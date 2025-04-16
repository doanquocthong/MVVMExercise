package com.example.mvvmexercise

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DataType(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var title : String,
    var description : String,
)