package com.example.akharinkhabar.data.model

import com.google.gson.annotations.SerializedName

/**
 * error ?
 * reason ?
 * myMessage ?
 */
data class ErrorModel(
    val error: String = "error",
    val id: Int = 1,
    var status :Int = 0,
    @SerializedName("message")
    var myMessage: String = "Try later",
    val reason: String = "",
    val unHandleError: String = ""
):Throwable()