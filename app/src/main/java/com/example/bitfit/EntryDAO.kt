package com.example.bitfit

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EntryDao {
    @Query("SELECT * FROM entry_table")
    fun getAll(): LiveData<List<EntryEntity>>

    @Insert
    fun insert(entry: EntryEntity)

    @Query("DELETE FROM entry_table")
    fun deleteAll()
}
