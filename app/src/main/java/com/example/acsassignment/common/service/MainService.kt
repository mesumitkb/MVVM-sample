package com.example.acsassignment.common.service

import com.example.acsassignment.model.Issue
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MainService {

    @GET("issues")
    fun fetchIssues(
        @Query(value = "sort") sortType: String
    ): Call<List<Issue>>

    @GET("issues/{id}/comments")
    fun fetchComments(
    @Path("id") issueNumber : Int
    ):Call<List<Issue>>

}