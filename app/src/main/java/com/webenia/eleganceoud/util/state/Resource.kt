package com.elegance_oud.util.state

import com.webenia.eleganceoud.util.state.UiText

sealed class Resource<T>(val data: T? = null, val message: UiText? = null) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(message: UiText, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>(){
        override fun equals(other: Any?): Boolean {
            return other is Loading<*>
        }

        override fun hashCode(): Int {
            return this::class.hashCode()
        }
    }
}
