package com.example.acsassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.acsassignment.model.Issue
import com.example.acsassignment.manager.MainManager

class IssueDetailViewModel(private val mainManager: MainManager) : ViewModel() {

    val issue = Issue()
    var commentList : MutableList<Issue>? = null

    private var _requestComments = MutableLiveData<Any>()
    val requestComments: LiveData<Any>
        get() = _requestComments

    fun fetchComments(issueNumber: Int) {
        mainManager.fetchComments(_requestComments, issueNumber)
    }
}