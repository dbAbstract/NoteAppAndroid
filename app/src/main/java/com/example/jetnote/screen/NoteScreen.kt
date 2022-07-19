package com.example.jetnote.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetnote.R
import com.example.jetnote.components.NoteButton
import com.example.jetnote.components.NoteInputText
import com.example.jetnote.components.NoteRow
import com.example.jetnote.data.NotesData
import com.example.jetnote.model.Note


@Composable
fun NoteScreen(notes: List<Note>, onAddNote: (Note) -> Unit, onRemoveNote: (Note) -> Unit){
    var title by remember {
        mutableStateOf("")
    }

    var desc by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.app_name))
            },
            actions = {
                Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "Icon")
            },
            backgroundColor = Color(0xFFDADFE3)
        )

        // Content
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(
                text = title,
                modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                label = "Title",
                onTextChange = {
                    if (it.all { char -> char.isLetter() || char.isWhitespace() } ) title = it
                }
            )

            NoteInputText(
                modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                text = desc,
                label = "Add a Note",
                onTextChange = {
                    if (it.all { char -> char.isLetter() || char.isWhitespace()}) desc = it
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            NoteButton(text = "Save",
                onClick = {
                      if (title.isNotEmpty() && desc.isNotEmpty()) {
                        onAddNote(Note(title = title, desc = desc))
                    }
                    Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
            })
        }

        Divider(modifier = Modifier.padding(10.dp))

        LazyColumn{
            items(notes) {
                note -> NoteRow(note = note, onNoteClicked = {
                    onRemoveNote(note)
                })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesScreenPreview() {
    NoteScreen(notes = NotesData().loadNotes(), onAddNote = {}, onRemoveNote = {})
}