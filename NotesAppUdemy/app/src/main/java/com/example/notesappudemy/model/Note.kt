package com.example.notesappudemy.model


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.LocalDate
import java.util.*


@Entity(tableName = "notes_tbl")
data class Note @RequiresApi(Build.VERSION_CODES.O) constructor(
                @PrimaryKey
                val id: UUID = UUID.randomUUID(),

                @ColumnInfo(name ="note_title")
                val title:String,

                @ColumnInfo(name="note_description")
                val description:String,

                @ColumnInfo(name="note_date")
                val entryTime: Date = Date.from(Instant.now()))

