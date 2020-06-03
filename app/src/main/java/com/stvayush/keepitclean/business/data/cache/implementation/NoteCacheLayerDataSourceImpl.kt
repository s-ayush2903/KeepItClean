package com.stvayush.keepitclean.business.data.cache.implementation

import com.stvayush.keepitclean.business.data.cache.abstraction.NoteCacheLayerDataSource
import com.stvayush.keepitclean.business.domain.model.Note
import com.stvayush.keepitclean.framework.datasource.abstraction.NoteDaoService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteCacheLayerDataSourceImpl
@Inject
constructor(private val noteDaoService: NoteDaoService) : NoteCacheLayerDataSource {

  override suspend fun insertNote(note: Note) = noteDaoService.insertNote(note)

  override suspend fun deleteNote(primaryKey: String) = noteDaoService.deleteNote(primaryKey)

  override suspend fun deleteMultipleNotes(note: List<Note>) =
    noteDaoService.deleteMultipleNotes(note)

  override suspend fun updateNote(
    primaryKey: String,
    newTitle: String,
    newBody: String
  ) = noteDaoService.updateNote(primaryKey, newTitle, newBody)

  override suspend fun searchNotes(
    query: String,
    filterAndOrder: String,
    page: Int
  ) = noteDaoService.searchNotes(query, filterAndOrder, page)

  override suspend fun searchNoteById(primaryKey: String) =
    noteDaoService.searchNoteById(primaryKey)

  override suspend fun totalNotes() = noteDaoService.totalNotes()

  override suspend fun insertMultipleNotes(note: List<Note>) =
    noteDaoService.insertMultipleNotes(note)
}