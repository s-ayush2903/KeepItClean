package com.stvayush.keepitclean.business.data.network.utils

import com.stvayush.keepitclean.business.data.network.utils.ApiResult.GenericError
import com.stvayush.keepitclean.business.data.network.utils.ApiResult.NetworkError
import com.stvayush.keepitclean.business.data.network.utils.ApiResult.Success
import com.stvayush.keepitclean.business.domain.state.DataState
import com.stvayush.keepitclean.business.domain.state.StateEvent
import com.stvayush.keepitclean.business.domain.state.StateResource.MessageType
import com.stvayush.keepitclean.business.domain.state.StateResource.MessageType.Error
import com.stvayush.keepitclean.business.domain.state.StateResource.Response
import com.stvayush.keepitclean.business.domain.state.StateResource.UIComponentType
import com.stvayush.keepitclean.business.domain.state.StateResource.UIComponentType.Dialog

/** Documentation same as CacheResponseHandler */
abstract class ApiResponseHandler<ViewState, Data>(
  private val apiResultResponse: ApiResult<Data?>,
  private val stateEvent: StateEvent?
) {
  suspend fun getResult(): DataState<ViewState>? {

    return when (apiResultResponse) {

      is GenericError -> {
        DataState.error(
          response = Response(
            message = "${stateEvent?.errorInfo()}\n" +
              " Reason: ${apiResultResponse.errorMessage}",
            uiComponentType = Dialog(),
            /** If you're confused nigga, then see StateResource
             *  package path(com.stvayush.keepitclean.business.domain.state)
             * */
            messageType = Error()
          ), stateEvent = stateEvent
        )
      }

      is NetworkError -> {
        DataState.error(
          response = Response(
            message = "${stateEvent?.errorInfo()}\n" +
              " Reason: ${NetworkErrors.NETWORK_ERROR}",
            uiComponentType = Dialog(),
            /** If you're confused nigga, then see StateResource
             *  package path(com.stvayush.keepitclean.business.domain.state)
             * */
            messageType = Error()
          ), stateEvent = stateEvent
        )
      }

      is Success -> {
        if (apiResultResponse.value == null) {
          DataState.error(
            response = Response(
              message = "${stateEvent?.errorInfo()}\n" +
                " Reason: ${NetworkErrors.NETWORK_DATA_NULL}",
              uiComponentType = UIComponentType.Dialog(),
              /** If you're confused nigga, then see StateResource */
              messageType = MessageType.Error()
            ), stateEvent = stateEvent
          )

        } else {
          handleSuccess(apiResultResponse.value)
        }
      }

    }

  }

  abstract fun handleSuccess(resultObject: Data): DataState<ViewState>

}