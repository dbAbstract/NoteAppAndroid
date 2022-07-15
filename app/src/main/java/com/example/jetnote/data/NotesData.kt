package com.example.jetnote.data

import com.example.jetnote.model.Note


class NotesData {
    fun loadNotes(): List<Note> {
        return listOf(

            Note(title = "A good day", desc = "We went on a vacation by the lake"),
            Note(title = "Android Compose", desc = "Working on Android Compose course today"),
            Note(title = "Keep at it...", desc = "Sometimes things just happen"),
            Note(title = "A movie day", desc = "Watching a movie with family today"),
            Note(title = "A movie day", desc = "Watching a movie with family today"),
            Note(title = "A movie day", desc = "Watching a movie with family today"),
            Note(title = "A movie day", desc = "Watching a movie with family today"),
            Note(title = "A movie day", desc = "Watching a movie with family today"),
            Note(title = "A movie day", desc = "Watching a movie with family today"),
            Note(title = "A movie day", desc = "Watching a movie with family")

        )
    }
}