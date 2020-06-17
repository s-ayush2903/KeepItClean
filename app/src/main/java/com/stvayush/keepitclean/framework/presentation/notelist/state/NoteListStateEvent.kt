package com.stvayush.keepitclean.framework.presentation.notelist.state

import com.stvayush.keepitclean.business.domain.model.Note
import com.stvayush.keepitclean.business.domain.state.StateEvent
import com.stvayush.keepitclean.business.domain.state.StateResource

sealed class NoteListStateEvent : StateEvent {

  class InsertNewNoteStateEvent(
    val title: String
  ) : NoteListStateEvent() {

    override fun errorInfo() = "Error Inserting New Note"
    override fun eventName() = "Insert New Note Event"
    override fun shouldDisplayProgressBar(): Boolean = true
  }

  class InsertMultipleNotes(
    val numNotes: Int
  ) : NoteListStateEvent() {

    override fun errorInfo() = "Error inserting Multiple Notes"
    override fun eventName() = "Insert Multiple Notes"
    override fun shouldDisplayProgressBar(): Boolean = true
  }

  class DeleteNoteEvent(
    var note: Note
  ) : NoteListStateEvent() {

    override fun errorInfo() = "Error Deleting Note"
    override fun eventName() = "Delete Multiple Notes"
    override fun shouldDisplayProgressBar() = true

  }

  class DeleteMultipleNotes(
    var notes: ArrayList<Note>
  ) : NoteListStateEvent() {

    override fun errorInfo() = "Error Deleting Multiple Notes"
    override fun eventName() = "Delete Multiple Notes"
    override fun shouldDisplayProgressBar() = true
  }

  class RestoreDeletedNote(
    var note: Note
  ) : NoteListStateEvent() {

    override fun errorInfo() = "Error Restoring Note"
    override fun eventName() = "Restore Note Event"
    override fun shouldDisplayProgressBar() = true
  }

  class SearchNotesEvent(
    var clearLayoutManagerCache: Boolean
  ) : NoteListStateEvent() {

    override fun errorInfo() = "Error Finding Note"
    override fun eventName() = "Search Notes Event"
    override fun shouldDisplayProgressBar() = true
  }

  class GetTotalCachedNotes : NoteListStateEvent() {

    override fun errorInfo() = "Error retrieving number of notes in cache"
    override fun eventName() = "Get Total number of Cached Notes"
    override fun shouldDisplayProgressBar() = true
  }

  class CreateStateMessageEvent(
    val stateMessage: StateResource.StateMessage
  ) : NoteListStateEvent() {

    override fun errorInfo() = "Error creating message for state event"
    override fun eventName() = "Create State Message Event"
    override fun shouldDisplayProgressBar() = false
  }

}