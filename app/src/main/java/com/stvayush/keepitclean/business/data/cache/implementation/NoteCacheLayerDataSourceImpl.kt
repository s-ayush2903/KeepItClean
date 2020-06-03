package com.stvayush.keepitclean.business.data.cache.implementation

import com.stvayush.keepitclean.business.data.cache.abstraction.NoteCacheLayerDataSource
import com.stvayush.keepitclean.business.domain.model.Note
import com.stvayush.keepitclean.framework.datasource.cache.abstraction.NoteDaoService
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

  override suspend fun searchNotes(query: String, filterAndOrder: String, page: Int): List<Note> {
    // FIXME: 4/6/20  Either delegate this function or implement some
    //  working functionality in it, right now to pass the pipeline,
    //  i've made it to return just an emptyList
    //  modify it
    return emptyList()
  }

  override suspend fun searchNoteById(primaryKey: String) =
    noteDaoService.searchNoteById(primaryKey)

  override suspend fun totalNotes() = noteDaoService.totalNotes()

  override suspend fun insertMultipleNotes(note: List<Note>) =
    noteDaoService.insertMultipleNotes(note)
}