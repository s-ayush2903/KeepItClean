package com.stvayush.keepitclean.business.data.network

import com.stvayush.keepitclean.business.data.network.abstraction.NoteNetworkDataSource
import com.stvayush.keepitclean.business.domain.model.Note

/** Simulating Firestore Caching Service */
class FakeNoteNetworkDataSourceImpl constructor(
  private val notesData: HashMap<String, Note>,
  private val deletedNotesData: HashMap<String, Note>
) : NoteNetworkDataSource {

  override suspend fun insertOrUpdateNote(note: Note) {
    note.id?.let { notesData.put(it, note) }    //succinct null-safe call equivalent to notesData.put(note.id, note)
  }

  override suspend fun deleteNote(primaryKey: String) {
    notesData.remove(primaryKey)
  }

  override suspend fun restoreNote(note: Note) {
    note.id?.let { deletedNotesData.put(it, note) }
  }

  override suspend fun restoreMultipleNotes(notes: List<Note>) {
    for (note in notes) {
      note.id?.let { deletedNotesData.put(it, note) }
    }
  }

  override suspend fun deletePendingNote(note: Note) {
    notesData.remove(note.id)
  }

  override suspend fun getDeletedNotes(): List<Note> {
    return ArrayList(deletedNotesData.values)
  }

  override suspend fun deleteAllNotes() {
    deletedNotesData.clear()
  }

  override suspend fun getAllNotes(): List<Note> {
    return ArrayList(notesData.values)
  }

  override suspend fun searchNote(note: Note): Note? {
    return notesData[note.id]
  }

  override suspend fun insertOrUpdateMultipleNotes(notes: List<Note>) {
    for(note in notes){
      note.id?.let { notesData.put(it, note) }
    }
  }
}