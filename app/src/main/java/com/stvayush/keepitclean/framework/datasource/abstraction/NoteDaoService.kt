package com.stvayush.keepitclean.framework.datasource.abstraction

import com.stvayush.keepitclean.business.domain.model.Note

/** I just copied it from the @param[NoteCacheLayerDataSource](com.stvayush.keepitclean.business.data.cache.abstraction.notecachelayer)!
 *  trust me!
 *  (Oops, i went a bit overboard, i'll be modifying this file a bit, it won't be the exact copy of @param[NoteCacheLayerDataSource]
 * */

interface NoteDaoService {
  /** This function will be used for inserting the note in the cache */
  suspend fun insertNote(note: Note): Long

  suspend fun deleteNote(primaryKey: String): Int
  suspend fun deleteMultipleNotes(note: List<Note>): Int
  suspend fun updateNote(
    primaryKey: String,
    newTitle: String,
    newBody: String
  ): Int

  /** Wait nigga, i'll be modifying this function later on,
   *  right now its already too much on the commit tree,
   *  so leaving as of now */
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