package com.firebase.wrapper.admin.firestore

external interface QuerySnapshot {

    val docs: Array<QueryDocumentSnapshot>
    val size: Int
    val empty: Boolean

    fun forEach(callback: (QueryDocumentSnapshot) -> Unit, thisArg: dynamic = definedExternally)
}