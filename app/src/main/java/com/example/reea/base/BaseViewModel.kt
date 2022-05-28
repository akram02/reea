package com.example.reea.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reea.network.NetworkError
import kotlinx.coroutines.launch
import timber.log.Timber

open class BaseViewModel: ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val networkError = MutableLiveData<String>()

    fun <T : Any, U : Any> getResponse(
        requesterMethod: suspend(T) -> U,
        body: T,
        onResponseMethod: (response: U) -> Unit
    ) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                onResponseMethod(requesterMethod(body))
            }
            catch (it: Throwable) {
                networkError.value = NetworkError.getResponseMessage(it)
                Timber.e(it.localizedMessage)
            }
            isLoading.value = false
        }
    }


    fun <U : Any> getResponse(
        requesterMethod: suspend() -> U,
        onResponseMethod: (response: U) -> Unit
    ) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                onResponseMethod(requesterMethod())
            }
            catch (it: Throwable) {
                networkError.value = NetworkError.getResponseMessage(it)
                Timber.e(it.localizedMessage)
            }
            isLoading.value = false
        }
    }

}