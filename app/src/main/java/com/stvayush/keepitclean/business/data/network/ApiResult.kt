package com.stvayush.keepitclean.business.data.network

/** This @param[ApiResult] class is an equivalent to @param[CacheResult] class in cache package
 *  See that class for reference
 * */
sealed class ApiResult<out T> {

  data class Success<out T>(val value: T) : ApiResult<T>()

  data class GenericError(
    val code: Int? = null,
    val errorMessage: String? = null
  ) : ApiResult<Nothing>()


  /** For handling errors occurring due to networking of which
   *  we ain't exactly aware of but just know that they
   *  are happening due to network only */
  object NetworkError : ApiResult<Nothing>()

}
