package com.firebase.wrappers.admin

import com.firebase.wrappers.admin.database.Database
import com.firebase.wrappers.admin.firestore.Firestore
import com.firebase.wrappers.admin.storage.Storage

@JsModule("firebase-admin")
external object Admin {
    val credential: dynamic

    fun initializeApp(config: FirebaseAppOptions)
    fun database(): Database
    fun firestore(): Firestore
    fun storage(): Storage
}