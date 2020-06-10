package com.stvayush.keepitclean.business.domain.model

import com.stvayush.keepitclean.business.domain.utils.DateUtils
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.ArrayList


/** For generating/creating notes */

@Singleton
class NoteFactory
@Inject
constructor(
    private val dateUtils: DateUtils
) {

    fun createSingleNote(
        id: String? = null,
        title: String,
        body: String? = null
    ): Note {
        return Note(
            id = id ?: UUID.randomUUID().toString(),
            title = title,
            body = body ?: "",
            created_at = dateUtils.getTimestamp(),
            last_updated = dateUtils.getTimestamp()
        )
    }

    fun createNotesList(numNotes: Int): List<Note> {
        val list: ArrayList<Note> = ArrayList()
        for (int in 0 until numNotes)
            list.add(
                createSingleNote(
                    id = null,
                    title = UUID.randomUUID().toString(),
                    body = UUID.randomUUID().toString()
                )
            )
        return list
    }


}