package com.firebase.wrappers.admin.firestore

external interface Firestore {
    fun collection(collectionPath: String): CollectionReference
}