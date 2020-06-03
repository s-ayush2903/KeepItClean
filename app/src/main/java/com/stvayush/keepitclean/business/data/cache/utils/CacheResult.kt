package com.stvayush.keepitclean.business.data.cache.utils

/** This'll be emitted when a CacheRequest is made as
 *  @code[Success] or as @code[GenericError] which will be parsed into
 *  @param[DataState](package com.stvayush.keepitclean.business.domain.state.datastate){i don't know why hyperlinking isn't working here}
 *  because dataState is something
 *  of which a viewModel is aware of
 * */

sealed class CacheResult<out T> {

  data class Success<out T>(val value: T) : CacheResult<T>()

  data class GenericError(val errorMessage: String? = null) : CacheResult<Nothing>()

}