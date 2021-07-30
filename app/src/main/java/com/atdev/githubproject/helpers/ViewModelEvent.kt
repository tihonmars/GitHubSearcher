package com.atdev.githubproject.helpers


class ViewModelEvent<out T>(private val value: T) {

    private var hasBeenHandled = false

    fun getValueOnceOrNull(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            value
        }
    }

    fun getValue(): T = value
}