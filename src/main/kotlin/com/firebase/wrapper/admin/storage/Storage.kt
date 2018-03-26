package com.firebase.wrappers.admin.storage

external interface Storage {

    fun bucket(name: String? = definedExternally): Bucket
}