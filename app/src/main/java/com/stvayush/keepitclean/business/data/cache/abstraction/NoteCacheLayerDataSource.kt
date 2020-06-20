package com.stvayush.keepitclean.business.data.cache.abstraction

import com.stvayush.keepitclean.business.domain.model.Note

/** An Interface between LocalDataSource and SQLite
 *  Please do note that
 *  The only purpose of creating this class is that we'll be using this as helper class
 *  for room simulation in testing as well because i wanna unit test room not instrumentation one
 * */

/** This contains of all the functions that'll be performed in the cache */

interface NoteCacheLayerDataSource {

  /** This function will be used for inserting the note in the cache */
  suspend fun insertNote(note: Note): Long

  suspend fun deleteNote(primaryKey: String): Int
  suspend fun deleteMultipleNotes(note: List<Note>): Int
  suspend fun updateNote(
    primaryKey: String,
    newTitle: String,
    newBody: String,
    timestamp: String?
  ): Int

  suspend fun searchNotes(
    query: String,
    filterAndOrder: String,
    page: Int
  ): List<Note>

  suspend fun searchNoteById(primaryKey: String): Note?
  suspend fun totalNotes(): Int

  /** This function is solely made for testing purposes as we won't be adding a list of notes in the real app/use case */
  suspend fun insertMultipleNotes(note: List<Note>): LongArray

}