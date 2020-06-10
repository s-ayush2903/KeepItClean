package com.stvayush.keepitclean.business.interactors

import com.stvayush.keepitclean.business.data.cache.abstraction.NoteCacheLayerDataSource
import com.stvayush.keepitclean.business.data.network.abstraction.NoteNetworkDataSource
import com.stvayush.keepitclean.business.domain.model.NoteFactory
import com.stvayush.keepitclean.business.domain.state.DataState
import com.stvayush.keepitclean.business.interactors.notelist.InsertNewNote
import com.stvayush.keepitclean.di.DependencyContainer
import com.stvayush.keepitclean.framework.presentation.notelist.state.NoteListStateEvent
import com.stvayush.keepitclean.framework.presentation.notelist.state.NoteListViewState
import junit.framework.TestCase.*
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.util.*

/** I'll be testing it in 3 steps for a complete 100% coverage wala end to end test
 *  i) Check if a note is created successfully
 *  (Successful creation means that the note gets inserted in cache and firestore as well)
 *
 *  ii) Check a forced error from the cache to test if the cache really throws some error
 *  iii) Do same for firestore as well
 *  */

class InsertNewNoteTest {

    /** System in test */
    private val insertNewNote: InsertNewNote

    /** Get the deps */
    private val noteFactory: NoteFactory
    private val dependencyContainer: DependencyContainer
    private val noteCacheLayerDataSource: NoteCacheLayerDataSource
    private val noteNetworkDataSource: NoteNetworkDataSource

    init {
        dependencyContainer = DependencyContainer()
        dependencyContainer.build()
        noteCacheLayerDataSource = dependencyContainer.noteCacheLayerDataSource
        noteNetworkDataSource = dependencyContainer.noteNetworkDataSource
        noteFactory = dependencyContainer.noteFactory
        insertNewNote = InsertNewNote(noteCacheLayerDataSource, noteNetworkDataSource, noteFactory)
    }

    @InternalCoroutinesApi
    @Test
    fun successfulNoteInsertion() = runBlocking {
        val testNote = noteFactory.createSingleNote(id = null, title = UUID.randomUUID().toString())

        insertNewNote.insertNewNote(
            testNote.id,
            testNote.title,
            stateEvent = NoteListStateEvent.InsertNewNoteStateEvent(title = testNote.title)
        ).collect(object : FlowCollector<DataState<NoteListViewState>?> {
            override suspend fun emit(value: DataState<NoteListViewState>?) {
//                assertEquals(value?.stateMessage?.response?.message, "Successfully inserted new note in the cache")
                println(value?.stateMessage?.response)

                assertEquals(
                    "Successfully inserted new note in the cache",
                    value?.stateMessage?.response?.message
                )
            }
        })

        /** Caching confirmation */
        val pushedInCache = testNote.id?.let { noteCacheLayerDataSource.searchNoteById(it) }
        assertTrue(pushedInCache == testNote)

        /** Check pushing to firestore as well */
        val pushedInFirestore = noteNetworkDataSource.searchNote(testNote)
        assertTrue(pushedInFirestore == testNote)
    }


}