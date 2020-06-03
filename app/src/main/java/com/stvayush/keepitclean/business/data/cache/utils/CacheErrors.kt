package com.stvayush.keepitclean.business.data.cache.utils


/** An object that contains cache error strings defined in it */
object CacheErrors {

  const val CACHE_ERROR_UNKNOWN = "Unknown cache error "
  const val CACHE_ERROR_ = "Cache error "

  /** If a caching request takes more than the time defined in @param[CacheConstants]
   *  then it'll throw TLE/timeout
   *  */
  const val CACHE_ERROR_TIMEOUT = "Cache timeout error "

  /** If we search some data/note in the cache and isn't found then it'll return null */
  const val CACHE_DATA_NULL = "Cache data null/ not found "

}