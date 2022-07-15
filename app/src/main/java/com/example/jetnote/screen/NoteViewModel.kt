package com.example.jetnote.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetnote.model.Note
import com.example.jetnote.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {
    // Declares a Flow instance that has an associated state to it
    private val _noteList = MutableStateFlow<List<Note>>(emptyList<Note>())
    val noteList = _noteList.asStateFlow()

    // Uses the Repository API to get all the Notes in Room and store it in _noteList State Flow
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository
                .getAllNotes()
                .distinctUntilChanged()
                .collect { 
                    if (it.isNullOrEmpty()){
                        Log.d("Empty", "Empty list")
                    }
                    else {
                        _noteList.value = it
                    }
                }
        }
    }

    // No need to suspend these functions because asynchronicity was handled in the Repository layer
    fun addNote(note: Note) = viewModelScope.launch { repository.addNote(note) }
    fun removeNote(note: Note) = viewModelScope.launch { repository.deleteNote(note) }
    fun getAllNotes() = viewModelScope.launch { repository.getAllNotes() }
    fun updateNote(note: Note) = viewModelScope.launch { repository.updateNote(note) }

}
