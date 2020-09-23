package sontung.dangvu.colornote.database

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity (tableName = "note_table")
data class Note(
    @PrimaryKey
    val timeCreated : Long,
    var title : String,
    var content : String,
//    @Ignore var dueDate : Date,
//    var hasDone : Boolean = false
) {

}