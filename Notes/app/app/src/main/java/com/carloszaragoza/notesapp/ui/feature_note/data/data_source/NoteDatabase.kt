package com.carloszaragoza.notesapp.ui.feature_note.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.carloszaragoza.groceryapp.feature_main.data.utils.MyTypeConverters
import com.carloszaragoza.notesapp.ui.feature_login.data.data_source.UserRoomDao
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.notes_db.note_dbItem
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.user_room.User

@TypeConverters(value =  [MyTypeConverters::class])
@Database(
    entities = [note_dbItem::class, User::class],
    version = 13)
abstract class NoteDatabase:RoomDatabase(){

    abstract val loginDao : UserRoomDao
    abstract val orderDao : NoteDao

    companion object {
        const val DATABASE_NAME = "note_db"
    }
}
