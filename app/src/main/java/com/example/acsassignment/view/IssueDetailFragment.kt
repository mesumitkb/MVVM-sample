package com.example.acsassignment.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.acsassignment.*
import com.example.acsassignment.common.adapter.CommentsAdapter
import com.example.acsassignment.common.BaseFragment
import com.example.acsassignment.common.RecyclerViewClickListener
import com.example.acsassignment.common.Status
import com.example.acsassignment.model.Issue

import com.example.acsassignment.databinding.FragmentIssueDetailBinding
import com.example.acsassignment.globals.Globals
import com.example.acsassignment.common.utils.DefaultUIObserver
import com.example.acsassignment.common.utils.getViewModel
import com.example.acsassignment.viewmodel.IssueDetailViewModel
import com.example.acsassignment.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_issue_list.*


class IssueDetailFragment : BaseFragment(),
    RecyclerViewClickListener {

    private val viewModel: IssueDetailViewModel by lazy {
        getViewModel{ IssueDetailViewModel(Globals.mainManager()) }
    }

    private val sharedVieModel: SharedViewModel by navGraphViewModels(R.id.main_navigation)
    private var issueNumber: Int? = null
    private var commentAdapter: CommentsAdapter? = null
    private lateinit var binding: FragmentIssueDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        issueNumber = sharedVieModel.sharedData.value
        setCommentsObserver()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = DataBindingUtil.inflate<FragmentIssueDetailBinding>(
            inflater, R.layout.fragment_issue_detail, container, false
        ).apply {
            lifecycleOwner = activity
            viewmodel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchComments(issueNumber!!)
    }

    private fun setCommentsObserver() {
        setObserver(viewModel.requestComments,
            DefaultUIObserver(Observer {
                when (it) {
                    is Status.Success -> {
                        onSuccess(it)
                    }
                    is Status.Failure -> {
                        Globals.showToast("Failed")
                        Log.e("hi", "failed")
                    }
                }
            })
        )
    }

    private fun onSuccess(it: Status.Success) {
        viewModel.commentList = it.data as MutableList<Issue>
        if(viewModel.commentList.isNullOrEmpty()) {
            Toast.makeText(activity, "Comments not Available", Toast.LENGTH_SHORT).show()
            progress_bar.visibility = View.GONE
//            findNavController().popBackStack()
        }
        else {
            setupRecyclerView()
            binding.invalidateAll()
            progress_bar.visibility = View.GONE
        }
    }

    private fun setupRecyclerView() {
        val comments = viewModel.commentList
        recycler_view.also {
            it.layoutManager = LinearLayoutManager(context)
            commentAdapter = CommentsAdapter(
                comments!!,
                this
            )
            it.adapter = commentAdapter
        }
    }

    override fun onRecyclerViewItemClick(view: View, obj: Any, position: Int) {

    }
}
