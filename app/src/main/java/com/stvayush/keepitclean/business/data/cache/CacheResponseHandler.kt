package com.stvayush.keepitclean.business.data.cache

import com.stvayush.keepitclean.business.data.cache.CacheErrors.CACHE_DATA_NULL
import com.stvayush.keepitclean.business.domain.state.DataState
import com.stvayush.keepitclean.business.domain.state.StateEvent
import com.stvayush.keepitclean.business.domain.state.StateResource.MessageType
import com.stvayush.keepitclean.business.domain.state.StateResource.Response
import com.stvayush.keepitclean.business.domain.state.StateResource.UIComponentType

/** What this class does is that it takes two parameters as input- @code[cacheResultResponse] and @code[stateEvent]
 *  and now it has a function to handle and process the response obtained from the @param[CacheResult]
 *  which processes error and success accordingly and returns a nullable @code[DataState]
 *  which wraps around the @code[ViewState] (of which a ViewModel is capable of reading)
 *  also do note that the class @param[CacheResponseHandler] outputs the objects of type of @param[ViewState]
 *  */

abstract class CacheResponseHandler<ViewState, Data>(
  private val cacheResultResponse: CacheResult<Data?>, /** CacheResultResponse is the output emitted from @param[CacheResult]*/
  private val stateEvent: StateEvent?
) {
  suspend fun getResult(): DataState<ViewState>? {
    return when (cacheResultResponse) {
      is CacheResult.GenericError -> {
        DataState.error(
          response = Response(
            message = "${stateEvent?.errorInfo()}\n" +
              " Reason: ${cacheResultResponse.errorMessage}",
            uiComponentType = UIComponentType.Dialog(),
            /** If you're confused nigga, then see StateResource
             *  package path(com.stvayush.keepitclean.business.domain.state)
             * */
            messageType = MessageType.Error()
          ), stateEvent = stateEvent
        )
      }

      is CacheResult.Success -> {
        if (cacheResultResponse.value == null) {
          DataState.error(
            response = Response(
              message = "${stateEvent?.errorInfo()}\n" +
                " Reason: ${CACHE_DATA_NULL}",
              uiComponentType = UIComponentType.Dialog(),
              /** If you're confused nigga, then see StateResource */
              messageType = MessageType.Error()
            ), stateEvent = stateEvent
          )
        } else {
          handleSuccess(resultObject = cacheResultResponse.value)
        }
      }

    }
  }

  abstract fun handleSuccess(resultObject: Data): DataState<ViewState>

}