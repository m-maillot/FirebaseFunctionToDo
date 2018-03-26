package com.firebase.wrappers.admin.storage

data class WriteStreamOptions(
        val gzip: Boolean? = null,
        val metadata: FileMetaData? = null,
        val offset: Int? = null,
        val predefinedAct: String? = null,
        val private: Boolean? = null,
        val public: Boolean? = null,
        val resumable: Boolean? = null,
        val uri: String? = null,
        val validation: Boolean? = null
)

data class FileMetaData(
        val contentType: String? = null,
        val metadata: Map<String, Any?>? = null,
        val cacheControl: String? = null
)