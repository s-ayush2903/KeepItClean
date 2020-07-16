package com.stvayush.keepitclean.framework.presentation.notelist.state

import android.os.Parcelable
import com.stvayush.keepitclean.business.domain.model.Note
import com.stvayush.keepitclean.business.domain.state.ViewState
import kotlinx.android.parcel.Parcelize

/** ViewState is basically the ui
 *  Something that is being displayed on the screen
 * */

@Parcelize
data class NoteListViewState(

  var noteList: ArrayList<Note>?=null,
  var newNote: Note? = null,
  var pendingDelete: NotePendingDelete? = null,
  var searchNote: String? = null,
  var page: Int? = null,
  var searchQueryExhausted: Boolean? = null,
  var filter: String? = null,
  var sort: String? = null,
  var layoutManagerState: Parcelable? = null,
  var numNotesinCache: Int? = null
) :
  Parcelable, ViewState {

  @Parcelize
  data class NotePendingDelete(
    var note: Note? = null,
    val position: Int? = null
  ) : Parcelable
}