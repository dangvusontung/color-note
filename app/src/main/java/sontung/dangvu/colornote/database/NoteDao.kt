package sontung.dangvu.colornote.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
abstract class NoteDao {

    @Insert
    abstract suspend fun insert(note: Note)

    @Update
    abstract suspend fun update(note: Note)

    @Delete
    abstract suspend fun delete(note: Note)

    @Query("SELECT * FROM note_table")
    abstract fun getAllNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM note_table WHERE timeCreated = :timeCreated")
    abstract fun getNoteByTime(timeCreated: Long): Note

}