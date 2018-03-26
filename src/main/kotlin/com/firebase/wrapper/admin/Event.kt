package com.firebase.wrappers.admin

import com.firebase.wrappers.admin.database.DeltaSnapshot

external interface Event<T> {
    val data: DeltaSnapshot<T>
    val params: dynamic
}