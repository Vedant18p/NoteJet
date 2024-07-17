package com.example.noter.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noter.model.Note
import com.example.noter.noteRepository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel() {

    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.getAllNotes()
                .collect { listOfNotes ->
                    if (listOfNotes.isEmpty()) {
                        Log.d("Empty", "Empty List")
                    } else {
                        _noteList.value = listOfNotes
                    }
                }
        }
    }

    fun addNote(note: Note) = viewModelScope.launch {
        noteRepository.addNote(note)
        refreshNoteList() // Refresh the list after adding
    }

    fun getNoteById(id: String): Flow<Note> = flow{

        emit(noteRepository.getNoteById(id).first())
    }

    fun deleteAllNotes() = viewModelScope.launch {
        noteRepository.deleteAllNotes()
        refreshNoteList() // Refresh the list after deleting
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        noteRepository.updateNote(note)
        refreshNoteList() // Refresh the list after updating
    }

    fun removeNote(id: String) = viewModelScope.launch {
        noteRepository.deleteNote(id)
        refreshNoteList() // Refresh the list after deleting
    }

    private fun refreshNoteList() = viewModelScope.launch(Dispatchers.IO) {
        _noteList.value = noteRepository.getAllNotes().first() // Fetch the latest list
    }
}
