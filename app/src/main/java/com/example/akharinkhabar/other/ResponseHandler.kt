package com.example.akharinkhabar.other

import android.util.Log
import com.example.akharinkhabar.data.model.ErrorModel
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

open class ResponseHandler {
    //handle success request
    fun <T : Any> handleSuccess(data: T): Resource<T> {
        return Resource.success(data)
    }

    //handle error request
    fun <T : Any> handleException(e: Exception): Resource<T> {
        Log.i("Exception", "handleException: ")
        return when (e) {
            is SocketTimeoutException -> Resource.error(getErrorModel(e), null)
            is IOException -> Resource.offline(null)
            is HttpException -> Resource.error(getErrorModel(e), null)
            else -> Resource.error(getErrorModel(e), null)
        }
    }


    private fun getErrorModel(e: Exception): ErrorModel {
        try {
            if(e is SocketTimeoutException){
                //todo: handle time out
                return ErrorModel(myMessage = "")
            }else{
                val response = (e as HttpException).response()
                response?.let { res ->
                    val errorBody = res.errorBody()
                    errorBody?.let {
                        val gson = Gson();
                        val errorModel = gson.fromJson(it.string(), ErrorModel::class.java)
                        errorModel.status = res.code()
                        return errorModel
                    }
                }
//            var jsonString = """{"id":1,"description":"Test"}""";
                return ErrorModel()
            }
        } catch (i: Exception) {
            val error = ErrorModel()
            e.message?.let {
                error.myMessage = it
            }
            return error


        }

    }

}