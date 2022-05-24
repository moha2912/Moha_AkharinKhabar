package com.example.akharinkhabar.other

import android.util.Log
import com.example.akharinkhabar.data.model.ErrorModel


data class Resource<out T>(
    val statusResource: Status,
    val data: T?,
    val errorModel: ErrorModel
) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, ErrorModel())
        }

        fun <T> error(errorModel: ErrorModel, data: T?): Resource<T> {
            Log.i("errorModel", "error: " + data.toString() + "....." + errorModel.toString())
            return Resource(Status.ERROR, data, errorModel)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, ErrorModel())
        }

        fun <T> offline(data: T?): Resource<T> {
            return Resource(Status.OFFLINE, data, ErrorModel())
        }
    }

}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING,
    OFFLINE
}