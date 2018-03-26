package com.firebase.wrappers.admin.storage

external interface Bucket {

    val name: String

    fun file(name: String, options: BucketFileOptions? = definedExternally): File
}