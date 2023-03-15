package com.example.calenderdemo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EventsDao {

    @Insert
    suspend fun insertEvent(evenModel: EvenModel)

    @Query("select * from tbl_event")
    fun getAllData() : LiveData<List<EvenModel>>
}