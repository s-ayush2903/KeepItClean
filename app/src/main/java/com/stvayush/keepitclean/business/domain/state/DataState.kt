package com.stvayush.keepitclean.business.domain.state

/** Inspired by Resource.kt class from the googleSamples for android
 *  On receiving a response from the server, the class provides a callback
 *  that includes message, the involved stateEvent and the response(data)
 * */

data class DataState<T>(
    var stateMessage: StateMessage? = null,
    var data: T? = null,
    var stateEvent: StateEvent? = null
) {

    companion object {

        fun <T> error(
            response: Response,
            stateEvent: StateEvent?
        ): DataState<T> {
            return DataState(
                stateMessage = StateMessage(
                    response
                ),
                data = null,
                stateEvent = stateEvent
            )
        }

        fun <T> data(
            response: Response?,
            data: T? = null,
            stateEvent: StateEvent?
        ): DataState<T> {
            return DataState(
                stateMessage = response?.let {
                    StateMessage(
                        it
                    )
                },
                data = data,
                stateEvent = stateEvent
            )
        }
    }
}