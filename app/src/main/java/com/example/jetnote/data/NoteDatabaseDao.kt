package com.example.jetnote.data

import androidx.room.*
import com.example.jetnote.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao {

    @Query("SELECT * FROM NOTES_TBL")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM NOTES_TBL WHERE id =:id")
    suspend fun getNoteById(id: String): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: Note)

    @Query("DELETE FROM NOTES_TBL")
    suspend fun deleteAll(): Unit

    @Delete
    suspend fun deleteNote(note: Note)

}
