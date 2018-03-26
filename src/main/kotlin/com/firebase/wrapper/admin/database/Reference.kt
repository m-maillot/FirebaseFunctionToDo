package com.firebase.wrappers.admin.database

import com.firebase.wrappers.admin.CloudFunction
import com.firebase.wrappers.admin.Event
import com.firebase.wrappers.admin.FirebaseError
import kotlin.js.Promise


external interface Reference {
    val ref: Reference
    val key: String?
    val parent: Reference
    val root: Reference

    fun child(path: String): Reference
    fun push(value: Any?, onComplete: ((FirebaseError) -> Unit)? = definedExternally) : Promise<Any>
    fun set(value: Any): Promise<Unit>

    fun <T> onWrite(handler: (Event<T>) -> Unit) : CloudFunction
    fun <T> once(type: String): Promise<DeltaSnapshot<T>>
}