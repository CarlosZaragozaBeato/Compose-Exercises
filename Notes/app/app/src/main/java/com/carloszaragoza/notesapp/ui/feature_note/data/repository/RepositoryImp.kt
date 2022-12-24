package com.carloszaragoza.notesapp.ui.feature_note.data.repository

import com.carloszaragoza.notesapp.ui.feature_login.domain.model.notes_db.note_dbItem
import com.carloszaragoza.notesapp.ui.feature_note.data.data_source.NoteDao
import com.carloszaragoza.notesapp.ui.feature_note.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RepositoryImp @Inject constructor(
    private val dao: NoteDao
):Repository{
    override fun getNotes(): Flow<List<note_dbItem>> {
        return dao.getNotes()
    }

    override suspend fun insertNote(note: note_dbItem) {
        dao.insertNote(note)
    }

    override suspend fun deleteNote(note: note_dbItem) {
        dao.deleteNote(note)
    }

    override suspend fun getNoteById(id: Int?): note_dbItem? {
        return dao.getNoteById(id)
    }

}