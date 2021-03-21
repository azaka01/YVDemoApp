package com.intsoftdev.yvdemoapp.viewmodel

sealed class ResultState<out T> {

    data class Success<T>(val data: T) : ResultState<T>()

    object Busy : ResultState<Nothing>()

    data class Failure(
        val throwable: Throwable?
    ) : ResultState<Nothing>()

}