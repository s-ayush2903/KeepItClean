package com.stvayush.keepitclean.framework.datasource.cache.abstraction

import com.stvayush.keepitclean.business.domain.model.Note
import com.stvayush.keepitclean.framework.datasource.database.NOTE_PAGINATION_PAGE_SIZE

/** I just copied it from the @param[NoteCacheLayerDataSource](com.stvayush.keepitclean.business.data.cache.abstraction.notecachelayer)!
 *  and made a few modifications along with the implementation of few new functions that filter and sort the notes
 * */

interface NoteDaoService {

    /** This function will be used for inserting the note in the cache */
    suspend fun insertNote(note: Note): Long

    suspend fun deleteNote(primaryKey: String): Int
    suspend fun deleteMultipleNotes(note: List<Note>): Int
    suspend fun updateNote(
        primaryKey: String,
        newTitle: String,
        newBody: String
    ): Int

    /** Modified the search functionality */
    suspend fun searchNotes(): List<Note>

    /** Following functions search the queried note in the local database
     *  and sort 'em in the order as appended at the last of the function's name
     *  @param[pageSize] is the number of notes displayed per page, i.e., on the
     *  screen at once
     *  DESC stands for descending(decreasing) order
     *  ASC stands for ascending(increasing) order
     *  */

    suspend fun searchNOrderByDateDESC(
        query: String,
        page: Int,
        pageSize: Int = NOTE_PAGINATION_PAGE_SIZE
    ): List<Note>

    suspend fun searchNOrderByDateASC(
        query: String,
        page: Int,
        pageSize: Int = NOTE_PAGINATION_PAGE_SIZE
    ): List<Note>

    suspend fun searchNOrderByTitleASC(
        query: String,
        page: Int,
        pageSize: Int = NOTE_PAGINATION_PAGE_SIZE
    ): List<Note>

    suspend fun searchNOrderByTitleDESC(
        query: String,
        page: Int,
        pageSize: Int = NOTE_PAGINATION_PAGE_SIZE
    ): List<Note>

    suspend fun searchNoteById(primaryKey: String): Note?
    suspend fun totalNotes(): Int

    /** This function is solely made for testing purposes as we won't be adding a list of notes in the real app/use case */
    suspend fun insertMultipleNotes(note: List<Note>): LongArray

    suspend fun returnOrderedQuery(query: String, filterAndOrder: String, page: Int): List<Note>
}