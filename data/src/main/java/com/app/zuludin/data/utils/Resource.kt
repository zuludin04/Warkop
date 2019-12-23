package com.app.zuludin.data.utils

data class Resource<out T>(val status: Status, val data: T?, val error: Throwable?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(error: Throwable, data: T?): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                error
            )
        }

        fun <T> loading(): Resource<T> {
            return Resource(
                Status.LOADING,
                null,
                null
            )
        }
    }

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}