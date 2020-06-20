package com.stvayush.keepitclean.business.data.utils

import com.stvayush.keepitclean.business.data.cache.utils.CacheConstants.CACHE_TIMEOUT
import com.stvayush.keepitclean.business.data.cache.utils.CacheErrors.CACHE_ERROR_TIMEOUT
import com.stvayush.keepitclean.business.data.cache.utils.CacheErrors.CACHE_ERROR_UNKNOWN
import com.stvayush.keepitclean.business.data.cache.utils.CacheResult
import com.stvayush.keepitclean.business.data.network.utils.ApiResult
import com.stvayush.keepitclean.business.data.network.utils.NetworkConstants.NETWORK_TIMEOUT_LIM
import com.stvayush.keepitclean.business.data.network.utils.NetworkErrors.NETWORK_ERROR_TIMEOUT
import com.stvayush.keepitclean.business.data.network.utils.NetworkErrors.NETWORK_ERROR_UNKNOWN
import com.stvayush.keepitclean.business.domain.utils.cLog
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import retrofit2.HttpException
import java.io.IOException

/** A class for making safe api and caching calls */

object SafeCalls {

  suspend fun <T> safeCacheCall(
    dispatcher: CoroutineDispatcher,
    cacheCall: suspend () -> T?
  ): CacheResult<T?> {
    return withContext(dispatcher) {
      try {
        //try cachin' timeout
        withTimeout(CACHE_TIMEOUT) {
          CacheResult.Success(cacheCall.invoke())
        }
      } catch (throwable: Throwable) {
        throwable.printStackTrace()
        when (throwable) {
          is TimeoutCancellationException -> {
            CacheResult.GenericError(CACHE_ERROR_TIMEOUT)
          }
          else -> {
            CacheResult.GenericError(CACHE_ERROR_UNKNOWN)
          }
        }
      }
    }
  }

  suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T?
  ): ApiResult<T?> {
    return withContext(dispatcher) {

      try {

        withTimeout(NETWORK_TIMEOUT_LIM) {
          ApiResult.Success(apiCall.invoke())
        }
      } catch (throwable: Throwable) {
        throwable.printStackTrace()
        when (throwable) {

          is TimeoutCancellationException -> {
            val code = 408
            ApiResult.GenericError(code, NETWORK_ERROR_TIMEOUT)
          }

          is IOException -> {
            ApiResult.NetworkError
          }

          is HttpException -> {
            val code = throwable.code()
            val errorResponse = throwable.toString()
            cLog(errorResponse)
            ApiResult.GenericError(code, errorResponse)
          }

          else -> {
            ApiResult.GenericError(null, NETWORK_ERROR_UNKNOWN)
          }

        }
      }

    }
  }

}