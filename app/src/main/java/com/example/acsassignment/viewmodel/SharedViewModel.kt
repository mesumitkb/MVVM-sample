package com.example.acsassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.acsassignment.common.Status
import com.example.acsassignment.model.Issue
import com.example.acsassignment.manager.MainManager

class SharedViewModel(private val mainManager: MainManager) : ViewModel() {
    val issue = Issue()
    var issueList : MutableList<Issue>? = null

    private var _requestFunction = MutableLiveData<Any>()
    val requestFunction: LiveData<Any>
        get() = _requestFunction

    val sharedData = MutableLiveData<Int>()

    fun setSharedData(issueNumber: Int) {
        sharedData.value = issueNumber
    }

    fun fetchIssues() {
//        if(mainManager.isDataInLocal)
//            _requestFunction.value = Status.Success(mainManager.retrieveIssueData())
//        else
            mainManager.fetchIssues(_requestFunction)
    }
}