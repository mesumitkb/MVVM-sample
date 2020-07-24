package com.example.acsassignment.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.acsassignment.R
import com.example.acsassignment.common.RecyclerViewClickListener
import com.example.acsassignment.model.Issue
import com.example.acsassignment.databinding.CommentItemBinding

class CommentsAdapter(private val issues: MutableList<Issue>, private val listener: RecyclerViewClickListener) : RecyclerView.Adapter<CommentsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            DataBindingUtil.inflate<CommentItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.comment_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.commentItemBinding
        val issue = issues[position]
        binding.issue = issue
        binding.issueCard.setOnClickListener {
            listener.onRecyclerViewItemClick(binding.issueCard, issue, position)
        }
    }

    override fun getItemCount() = issues.size

    inner class ViewHolder(
        val commentItemBinding: CommentItemBinding
    ) : RecyclerView.ViewHolder(commentItemBinding.root)

}
