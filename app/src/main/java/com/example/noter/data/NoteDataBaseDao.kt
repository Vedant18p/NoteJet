package com.example.noter.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.noter.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDataBaseDao {
    @Query("SELECT * FROM notes_tbl")
    fun getNotes() :
            Flow<List<Note>>

    @Query("SELECT * FROM notes_tbl where id =:id")
    fun getNoteById(id : String) : Flow<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note : Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note : Note)

    @Query("DELETE from notes_tbl")
    suspend fun deleteAll()

    @Query("DELETE FROM notes_tbl where id =:id")
    suspend fun deleteNote(id : String)
}