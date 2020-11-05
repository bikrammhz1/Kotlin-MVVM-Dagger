package com.rbmhz.actwtk.di.module.storage

interface Storage {
    fun setString(key: String, value: String)
    fun getString(key: String): String
}
