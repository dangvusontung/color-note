package sontung.dangvu.colornote.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "note_table")
data class Note(
    var title: String,
    var content: String,
//    @Ignore var dueDate : Date,
//    var hasDone : Boolean = false
) : Serializable {
    @PrimaryKey
    var timeCreated: Long = System.currentTimeMillis()

}