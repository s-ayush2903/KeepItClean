package com.stvayush.keepitclean.framework.datasource.network.abstraction

import com.stvayush.keepitclean.business.domain.model.Note

interface NoteFirestoreService{

    /** Following series of function will be called for the firestore */

    /** For inserting or updating the note in the firestore as soon as it gets changed on user's device */
    suspend fun insertOrUpdateNote(note: Note)

    /** Lemme explain the delete functionality nigga,
     *  When a note is deleted(in this case the function called is @code[deleteNote]),
     *  a snackbar appears for a few seconds showing the option to undo the operation
     *  if undo is clicked then the note is restored(for restoration, the @code[restoreNote] is called)
     *  when used for multiple notes, the functions called are @code[restoreMultipleNotes]
     *  */

    suspend fun deleteNote(primaryKey: String)
    suspend fun deleteAllNotes()
    suspend fun restoreNote(note: Note)
    suspend fun restoreMultipleNotes(note: List<Note>)

    /** Pending note is the note that is
     *  kept on hold for deleting or restoring the note based on the input received from user via displayed snackBar
     *  Or
     *  shadowed by the presence of snackBar(quite technical and succinct definition)*/
    suspend fun deletePendingNote(note: Note)

    /** For obtaining the deleted notes */
    suspend fun getDeletedNotes(): List<Note>

    /** For getting the total number of active notes(notes that ain't deleted and are present now) in the firestore */
    suspend fun getAllNotes(): List<Note>
    suspend fun searchNote(note: Note): Note?

    /** For testing cases only */
    suspend fun insertOrUpdateMultipleNotes(note: List<Note>)


}