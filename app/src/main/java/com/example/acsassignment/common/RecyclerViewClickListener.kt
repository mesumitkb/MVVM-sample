package com.example.acsassignment.common

import android.view.View

interface RecyclerViewClickListener {
    fun onRecyclerViewItemClick(view: View, obj: Any, position: Int)
}