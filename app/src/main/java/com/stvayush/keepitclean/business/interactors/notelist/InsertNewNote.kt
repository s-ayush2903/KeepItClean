package com.stvayush.keepitclean.business.interactors.notelist

import com.stvayush.keepitclean.business.data.cache.abstraction.NoteCacheLayerDataSource
import com.stvayush.keepitclean.business.data.cache.utils.CacheResponseHandler
import com.stvayush.keepitclean.business.data.network.abstraction.NoteNetworkDataSource
import com.stvayush.keepitclean.business.data.utils.SafeCalls
import com.stvayush.keepitclean.business.domain.model.Note
import com.stvayush.keepitclean.business.domain.model.NoteFactory
import com.stvayush.keepitclean.business.domain.state.DataState
import com.stvayush.keepitclean.business.domain.state.StateEvent
import com.stvayush.keepitclean.business.domain.state.StateResource.MessageType
import com.stvayush.keepitclean.business.domain.state.StateResource.Response
import com.stvayush.keepitclean.business.domain.state.StateResource.UIComponentType
import com.stvayush.keepitclean.framework.presentation.notelist.state.NoteListViewState
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.UUID

/** In the noteListFragment, there'll be an option to
 *  add a new note
 * */

class InsertNewNote(
  private val noteCacheLayerDataSource: NoteCacheLayerDataSource,
  private val noteNetworkDataSource: NoteNetworkDataSource,
  private val noteFactory: NoteFactory
) {

  fun insertNewNote(
    id: String? = null,
    title: String,
    stateEvent: StateEvent
  ): Flow<DataState<NoteListViewState>?> = flow {

    val newNote = noteFactory.createSingleNote(
      id = id ?: UUID.randomUUID()
        .toString(),
      title = title,
      body = ""
    )

    /** Push the note in the cache */
    val cacheResult = SafeCalls.safeCacheCall(IO){
          noteCacheLayerDataSource.insertNote(newNote)
        }

    val cacheResponse = object:CacheResponseHandler<NoteListViewState, Long>(
      cacheResultResponse = cacheResult,
      stateEvent = stateEvent
    ){
      override fun handleSuccess(resultObject: Long): DataState<NoteListViewState> {
        return if (resultObject > 0) {
          val viewState = NoteListViewState(newNote = newNote)
          DataState.data(
            response = Response(
              message = "Successfully inserted new note in the cache",
              uiComponentType = UIComponentType.Toast(),
              messageType = MessageType.Success()
            ),
            data = viewState,
            stateEvent = stateEvent
          )

        } else {
          DataState.data(
            response = Response(
              message = "Error inserting note in cache",
              uiComponentType = UIComponentType.Toast(),
              messageType = MessageType.Error()
            ), data = null,
            stateEvent = stateEvent
          )
        }

      }
    }.getResult()

    emit(cacheResponse)

    /** Now update it in firestore as well */
    updateNetwork(cacheResponse!!.stateMessage!!.response.message,newNote)

  }

  private suspend fun updateNetwork(cacheResponse:String?, newNote:Note){
    if(cacheResponse.equals("Successfully inserted new note in the cache")){
        SafeCalls.safeApiCall(IO){
          noteNetworkDataSource.insertOrUpdateNote(newNote)
        }
    }
  }

}