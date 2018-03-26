package com.firebase.wrappers.admin.firestore

external interface DocumentData

@Suppress("NOTHING_TO_INLINE")
inline operator fun DocumentData.get(field: String): Any? = asDynamic()[field]

@Suppress("NOTHING_TO_INLINE")
inline operator fun DocumentData.set(field: String, value: Any) {
    asDynamic()[field] = value
}