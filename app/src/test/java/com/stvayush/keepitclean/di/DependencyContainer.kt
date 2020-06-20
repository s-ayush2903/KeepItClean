package com.stvayush.keepitclean.di

import com.stvayush.keepitclean.business.data.cache.FakeCacheDataSourceImpl
import com.stvayush.keepitclean.business.data.cache.abstraction.NoteCacheLayerDataSource
import com.stvayush.keepitclean.business.data.network.FakeNoteNetworkDataSourceImpl
import com.stvayush.keepitclean.business.data.network.abstraction.NoteNetworkDataSource
import com.stvayush.keepitclean.business.domain.model.NoteFactory
import com.stvayush.keepitclean.business.domain.utils.DateUtils
import com.stvayush.keepitclean.business.domain.utils.isUnitTest
import java.text.SimpleDateFormat
import java.util.Locale

/** It is kinda base Testing class that most of the concerned use cases will extend to
 *  Don't be afraid by its name!
 * */
class DependencyContainer {

  private val dateFormat = SimpleDateFormat("yyyy-mm-dd hh:mm:ss", Locale.ENGLISH)
  val dateUtil = DateUtils(dateFormat)
  lateinit var noteFactory: NoteFactory
  lateinit var noteCacheLayerDataSource: NoteCacheLayerDataSource
  lateinit var noteNetworkDataSource: NoteNetworkDataSource

  init {
    isUnitTest = true  //set it true to make use of logger.kt
  }

  fun build() {

    noteFactory = NoteFactory(dateUtil)
    noteNetworkDataSource = FakeNoteNetworkDataSourceImpl(notesData = HashMap(), deletedNotesData = HashMap())
    noteCacheLayerDataSource = FakeCacheDataSourceImpl(notesData = HashMap(), dateUtils = dateUtil)

  }

}