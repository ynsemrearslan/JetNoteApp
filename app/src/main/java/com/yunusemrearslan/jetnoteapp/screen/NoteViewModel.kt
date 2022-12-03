package com.yunusemrearslan.jetnoteapp.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yunusemrearslan.jetnoteapp.model.Note
import com.yunusemrearslan.jetnoteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository:NoteRepository) :ViewModel(){
    private val _notesList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _notesList.asStateFlow()
    init {
        viewModelScope.launch (Dispatchers.IO){
            repository.getAllNotes().distinctUntilChanged().collect(){listOdNotes->
                if (listOdNotes.isNullOrEmpty()){
                    Log.d("Empty", "Empty List")
                }else{
                    _notesList.value = listOdNotes
                }
            }
        }
    }
    fun addNote (note: Note) = viewModelScope.launch { repository.addNote(note) }
    fun updateNote(note: Note) = viewModelScope.launch { repository.updateNote(note) }
    fun removeNote(note: Note) = viewModelScope.launch{repository.deleteNote(note)}
}