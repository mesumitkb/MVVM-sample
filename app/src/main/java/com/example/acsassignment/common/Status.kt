package com.example.acsassignment.common

sealed class Status {
    class Loading(val data: Any? = null) : Status()
    class Success(val data: Any?) : Status()
    class Failure(val data: Any, val message: String?) : Status()
}