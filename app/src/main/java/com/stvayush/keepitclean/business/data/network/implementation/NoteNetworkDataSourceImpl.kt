package com.stvayush.keepitclean.business.data.network.implementation

import com.stvayush.keepitclean.business.data.network.abstraction.NoteNetworkDataSource
import com.stvayush.keepitclean.business.domain.model.Note
import com.stvayush.keepitclean.framework.datasource.abstraction.NoteFirestoreService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteNetworkDataSourceImpl
@Inject
constructor(private val firestoreService: NoteFirestoreService) : NoteNetworkDataSource {

    override suspend fun insertOrUpdateNote(note: Note)= firestoreService.insertOrUpdateNote(note)

    override suspend fun deleteNote(primaryKey: String) = firestoreService.deleteNote(primaryKey)

    override suspend fun restoreNote(note: Note) = firestoreService.restoreNote(note)

    override suspend fun restoreMultipleNotes(note: List<Note>) = firestoreService.restoreMultipleNotes(note)

    override suspend fun deletePendingNote(note: Note) = firestoreService.deletePendingNote(note)

    override suspend fun getDeletedNotes(): List<Note> = firestoreService.getDeletedNotes()

    override suspend fun getAllNotes(): List<Note> = firestoreService.getAllNotes()

    override suspend fun searchNote(note: Note): Note? = firestoreService.searchNote(note)

    override suspend fun insertOrUpdateMultipleNotes(note: List<Note>) = firestoreService.insertOrUpdateMultipleNotes(note)

}