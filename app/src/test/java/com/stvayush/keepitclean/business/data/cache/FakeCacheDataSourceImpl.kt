package com.stvayush.keepitclean.business.data.cache

import com.stvayush.keepitclean.business.data.cache.abstraction.NoteCacheLayerDataSource
import com.stvayush.keepitclean.business.domain.model.Note
import com.stvayush.keepitclean.business.domain.utils.DateUtils
import com.stvayush.keepitclean.framework.datasource.database.NOTE_PAGINATION_PAGE_SIZE
import simulations.errors.CachingErrors.FORCE_DELETE_NOTES_EXCEPTION
import simulations.errors.CachingErrors.FORCE_DELETE_NOTE_EXCEPTION
import simulations.errors.CachingErrors.FORCE_NEW_NOTE_EXCEPTION
import simulations.errors.CachingErrors.FORCE_SEARCH_NOTE_EXCEPTION
import simulations.errors.CachingErrors.FORCE_UPDATE_NOTE_EXCEPTION

/** A SQLite simulation
 *  Cakewalk, nothin' special just normal queries
 *  with little error handling
 * */
class FakeCacheDataSourceImpl constructor(
  private val notesData: HashMap<String, Note>,
  private val dateUtils: DateUtils
) : NoteCacheLayerDataSource {

  /** This function will be used for simulating note insertion in the cache */
  override suspend fun insertNote(note: Note): Long {

    if (note.id.equals(FORCE_NEW_NOTE_EXCEPTION)) {
      throw Exception("Something went wrong while inserting the note")
    }
    if (note.id.equals(FORCE_DELETE_NOTE_EXCEPTION)) {
      return -1
    }
    note.id?.let { notesData.put(it, note) }
    return -1
  }

  override suspend fun deleteNote(primaryKey: String): Int {

    when (primaryKey) {
      FORCE_DELETE_NOTE_EXCEPTION -> {
        throw Exception("Error deleting the note")
      }
      FORCE_DELETE_NOTES_EXCEPTION -> {
        throw Exception("Error Deleting multiple notes")
      }
      else -> {
        return notesData.remove(primaryKey)
          ?.let { 1 } ?: -1
        /** return 1 for success and in sqlite, -1 is returned if any error occurs */
      }
    }
  }

  override suspend fun deleteMultipleNotes(notesList: List<Note>): Int {
    var flag = 1
    for (note in notesList) {
      if (notesData.remove(note.id) == null) {
        flag = -1
      }
    }
    return flag
  }

  override suspend fun updateNote(
    primaryKey: String,
    newTitle: String,
    newBody: String,
    timestamp: String?
  ): Int {
    if (primaryKey == FORCE_UPDATE_NOTE_EXCEPTION) {
      throw Exception("Error updating note!")
    }

    val updatedNote = Note(
      id = primaryKey,
      title = newTitle,
      body = newBody ?: "",
      created_at = notesData[primaryKey]?.created_at ?: dateUtils.getTimestamp(),
      last_updated = timestamp ?: dateUtils.getTimestamp()
    )
    return notesData[primaryKey]
      ?.let {
        notesData[primaryKey] = updatedNote
        1 // success
      } ?: -1 // nothing to update
  }

  override suspend fun searchNotes(
    query: String,
    filterAndOrder: String,
    page: Int
  ): List<Note> {
    if (query == FORCE_SEARCH_NOTE_EXCEPTION) {
      throw Exception("Error searching the note!")
    }

    val results: ArrayList<Note> = ArrayList()
    for (note in notesData.values) {
      if (note.title!!.contains(query)) {
        results.add(note)
      } else if (note.body!!.contains(query)) {
        results.add(note)
      }
      if (results.size > (page * NOTE_PAGINATION_PAGE_SIZE))
        break
    }

    return results
  }

  override suspend fun searchNoteById(primaryKey: String) =
    notesData[primaryKey]   //returns the note at primaryKeyth position

  override suspend fun totalNotes() = notesData.size

  /** This function is solely made for testing purposes as we won't be adding a list of notes in the real app/use case */
  override suspend fun insertMultipleNotes(note: List<Note>): LongArray {

    val results = LongArray(note.size)

    note.withIndex()
      .forEach { (index, note) ->
      results[index] = 1
        note.id?.let { notesData[it] = note }
      }

    return results

  }

}