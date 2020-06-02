package com.stvayush.keepitclean.business.domain.model

/** About the package {model}:
 *  This package consists of the classes and objects that'll be used
 *  widely throughout the module with as it is format
 *  meaning that they won't be modified anywhere in the whole module
 *       Think it like this:
 *  that a Note Struct is never going to be modified in the app as
 *  it will always have the same attributes that it has here as
 *  a "Note is a Note"
 *  So, on top of it, we define it here and use whenever needed
 *  */

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/** Info Regarding the class[Note]
 *  A sample Note data class that defines and contains the attributes of a standard Note
 *  It is Annotated @Parcelable as it would be passed as bundle arguments laterly
 *  in the different use cases
 * **/

@Parcelize
data class Note(
    val id: String?,
    val title: String?,
    val body: String?,
    val created_at: String?,
    val last_updated: String?
) : Parcelable