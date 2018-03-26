package com.firebase.wrappers.admin.database

external interface DeltaSnapshot<T> {
    fun `val`(): T
}