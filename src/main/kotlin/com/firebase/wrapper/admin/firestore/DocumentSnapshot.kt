package com.firebase.wrappers.admin.firestore

external interface DocumentSnapshot {

    val exists: Boolean
    val id: String
    val ref: DocumentReference
    val metadata: SnapshotMetadata


    fun data(options: SnapshotOptions? = definedExternally): DocumentData?
    fun get(fieldPath:String, options: SnapshotOptions? = definedExternally): Any?
    fun isEqual(other: DocumentSnapshot): Boolean
}