package com.example.jetnote.data

import androidx.room.*
import com.example.jetnote.model.Note

@Dao
interface NoteDatabaseDao {

    @Query("SELECT * FROM NOTES_TBL")
    fun getNotes(): List<Note>

    @Query("SELECT * FROM NOTES_TBL WHERE id =:id")
    fun getNoteById(id: String): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(note: Note)

    @Query("DELETE FROM NOTES_TBL")
    fun deleteAll(): Unit

    @Delete
    fun deleteNote(note: Note)

}
