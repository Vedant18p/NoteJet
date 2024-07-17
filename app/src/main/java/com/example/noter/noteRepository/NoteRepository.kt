package com.example.noter.noteRepository

import android.util.Log
import com.example.noter.data.NoteDataBaseDao
import com.example.noter.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class NoteRepository @Inject constructor(private val noteDataBaseDao: NoteDataBaseDao) {
    private val _notesChanged = MutableSharedFlow<Note>()
    suspend fun addNote(note : Note) = noteDataBaseDao.insert(note)

    suspend fun updateNote(note: Note) = noteDataBaseDao.update(note)

    suspend fun deleteNote(id: String) = noteDataBaseDao.deleteNote(id)

    suspend fun deleteAllNotes() = noteDataBaseDao.deleteAll()

    fun getNoteById(id : String) = noteDataBaseDao.getNoteById(id).onEach {
        Log.d("NoteRepository", "Fetched note: $it") // Log the fetched note
    }

    fun getAllNotes() : Flow<List<Note>> = flow {
        emitAll(noteDataBaseDao.getNotes()) // Emit initial list
        _notesChanged.collect {
            emitAll(noteDataBaseDao.getNotes()) // Re-emit list when changes occur
        }
    }.flowOn(Dispatchers.IO).conflate()
}