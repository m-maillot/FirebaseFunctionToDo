@file:Suppress("unused")

package com.expressjs.wrapper

import kotlin.js.Date

external interface CookieOptions {
    val maxAge: Int?
    val signed: Boolean?
    val expires: Date?
    val httpOnly: Boolean?
    val path: String?
    val domain: String?
    val secure: Boolean? // | 'auto';
    val encode: (`val`: String) -> Unit
    val sameSite: Boolean // | string;
}