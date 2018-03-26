package com.firebase.wrappers.admin

data class FirebaseAppOptions(
        val credential: dynamic = undefined,
        val databaseAuthVariableOverride: Any? = undefined,
        val databaseURL: String? = undefined,
        val storageBucket: String? = undefined,
        val projectId: String? = undefined
)