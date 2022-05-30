package com.example.reea.network

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.google.gson.JsonIOException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.CancellationException

object NetworkError {
    fun getResponseMessage(throwable: Throwable): String {
        when {
            throwable is SocketTimeoutException -> {
                return "Connection timeout"
            }
            throwable is IOException -> {
                return "Network error"
            }
            throwable is JsonIOException -> {
                return "Json convert error"
            }
            throwable is IllegalArgumentException -> {
                return throwable.message.toString()
            }
            throwable is CancellationException -> {
                return "Request rejected"
            }
            throwable !is HttpException -> {
                throw throwable
            }
            getErrorCode(throwable) == 401 || getErrorCode(throwable) == 403 -> {
                return "Access Denied"
            }
            else -> return "Unknown Error"
        }
    }

    private fun getErrorCode(throwable: Throwable): Int {
        return (throwable as HttpException).code()
    }

    fun getErrorMessage(loadState: CombinedLoadStates): String? {
        val error = when {
            loadState.append is LoadState.Error -> loadState.append
            loadState.prepend is LoadState.Error -> loadState.prepend
            loadState.refresh is LoadState.Error -> loadState.refresh
            else -> null
        }
        return if (error==null) null
        else (getResponseMessage((error as LoadState.Error).error))
    }
}