package com.example.acsassignment.model

data class Issue (

    val url : String? = null,
    val repository_url : String? = null,
    val labels_url : String? = null,
    val comments_url : String? = null,
    val events_url : String? = null,
    val html_url : String? = null,
    val id : Int? = null,
    val node_id : String? = null,
    val number : Int? = null,
    val title : String? = null,
    val user : User? = null,
    val labels : List<Label>? = null,
    val state : String? = null,
    val locked : Boolean? = null,
    val comments : Int? = null,
    val created_at : String? = null,
    val updated_at : String? = null,
    val closed_at : String? = null,
    val author_association : String? = null,
    val active_lock_reason : String? = null,
    val pull_request : PullRequest? = null,
    val body : String? = null
)
