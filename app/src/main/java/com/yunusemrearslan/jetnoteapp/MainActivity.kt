package com.yunusemrearslan.jetnoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yunusemrearslan.jetnoteapp.screen.NoteScreen
import com.yunusemrearslan.jetnoteapp.screen.NoteViewModel
import com.yunusemrearslan.jetnoteapp.ui.theme.JetNoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetNoteAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val noteViewModel = viewModel<NoteViewModel>()
                    NotesApp(noteViewModel = noteViewModel)
                }
            }
        }
    }
}
@ExperimentalComposeUiApi
@Composable
fun NotesApp(noteViewModel: NoteViewModel){
    val notesList = noteViewModel.noteList.collectAsState().value
    NoteScreen(notes = notesList, onAddNote ={noteViewModel.addNote(it)} , onRemoveNote ={noteViewModel.removeNote(it)} )
}
