package com.rbmhz.actwtk.api.imgur

data class ResponseModel<T>(
    val data: T,
    val success: Boolean,
    val status: Int
)