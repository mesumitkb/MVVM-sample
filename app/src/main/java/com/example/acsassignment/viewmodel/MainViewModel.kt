package com.example.acsassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.acsassignment.manager.MainManager

class MainViewModel(private val mainManager: MainManager) : ViewModel() {

    var username = "9760868269"

    private var _requestFunction = MutableLiveData<Any>()
    val requestFunction: LiveData<Any>
        get() = _requestFunction

    fun request() {
       // mainManager.verifyUser(username, _requestFunction)
    }
}