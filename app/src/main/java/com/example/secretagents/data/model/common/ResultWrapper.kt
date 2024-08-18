package com.example.secretagents.data.model.common


sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class GenericError(val error: KError? = null) : ResultWrapper<Nothing>()
    object Loading : ResultWrapper<Nothing>()
}