package com.example.calenderdemo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_event")
data class EvenModel(
    @PrimaryKey(autoGenerate = true)
    val id :  Long = 0,
    val name : String,
    val date : String,
)
