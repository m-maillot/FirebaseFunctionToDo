package com.firebase.wrappers.admin.database

external interface Database {
    fun ref(path: String): Reference
}