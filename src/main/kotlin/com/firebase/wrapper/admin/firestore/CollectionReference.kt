package com.firebase.wrappers.admin.firestore

import com.firebase.wrapper.admin.firestore.QuerySnapshot
import kotlin.js.Promise

external interface CollectionReference {
    val id: String
    val parent: DocumentReference?
    val path: String

    fun get(): Promise<QuerySnapshot>
    fun doc(documentPath: String?): DocumentReference
    fun add(data: DocumentData): Promise<DocumentReference>
}