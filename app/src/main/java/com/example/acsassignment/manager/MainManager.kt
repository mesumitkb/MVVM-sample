package com.example.acsassignment.manager

import androidx.lifecycle.MutableLiveData
import com.example.acsassignment.common.Status
import com.example.acsassignment.model.Issue
import com.example.acsassignment.common.service.MainService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainManager(private val mainService: MainService, private val storeManager: StoreManager) {

    var isDataInLocal : Boolean = false

    fun fetchIssues(mutableLiveData: MutableLiveData<Any>) {
        mutableLiveData.value = Status.Loading("Loading..")
        mainService.fetchIssues("updated").enqueue(
            object: Callback<List<Issue>> {
                override fun onFailure(call: Call<List<Issue>>, t: Throwable) {
                    mutableLiveData.value = Status.Failure(t, t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<List<Issue>>,
                    response: Response<List<Issue>>
                ) {
                    mutableLiveData.value = Status.Success(response.body())
                    saveIssueData(response.body()!!)
                    isDataInLocal = true
                }

            }
        )
    }

    fun fetchComments(mutableLiveData: MutableLiveData<Any>, issueNumber: Int) {
        mutableLiveData.value = Status.Loading("Loading..")
        mainService.fetchComments(issueNumber).enqueue(
            object: Callback<List<Issue>> {
                override fun onFailure(call: Call<List<Issue>>, t: Throwable) {
                    mutableLiveData.value = Status.Failure(t, t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<List<Issue>>,
                    response: Response<List<Issue>>
                ) {
                    mutableLiveData.value = Status.Success(response.body())
                }

            }
        )
    }

    fun saveIssueData(data: List<Issue>) {
        storeManager.saveObject(data, "KEY_ISSUE")
    }

    fun retrieveIssueData() : List<Issue> {
        return storeManager.getObject<List<Issue>>("KEY_ISSUE")!!
    }

}