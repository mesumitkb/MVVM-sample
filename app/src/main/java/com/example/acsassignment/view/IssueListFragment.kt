package com.example.acsassignment.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.acsassignment.*
import com.example.acsassignment.common.adapter.IssueAdapter
import com.example.acsassignment.common.BaseFragment
import com.example.acsassignment.common.RecyclerViewClickListener
import com.example.acsassignment.common.Status
import com.example.acsassignment.model.Issue
import com.example.acsassignment.databinding.FragmentIssueListBinding
import com.example.acsassignment.common.factory.SharedViewModelFactory
import com.example.acsassignment.globals.Globals
import com.example.acsassignment.common.utils.DefaultUIObserver
import com.example.acsassignment.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_issue_list.*


class IssueListFragment : BaseFragment(),
    RecyclerViewClickListener {

    private val factory = SharedViewModelFactory(Globals.mainManager())
    private val viewModel: SharedViewModel by navGraphViewModels(R.id.main_navigation) {
        factory
    }

    private var issueAdapter: IssueAdapter? = null

    private lateinit var binding: FragmentIssueListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRequestObserver()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentIssueListBinding>(
            inflater, R.layout.fragment_issue_list, container, false
        ).apply {
            lifecycleOwner = activity
            viewmodel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchIssues()
    }

    private fun setRequestObserver() {
		setObserver(viewModel.requestFunction,
            DefaultUIObserver(Observer {
                when (it) {
                    is Status.Success -> {
                        onSuccess(it)
                    }
                    is Status.Failure -> {
                        Globals.showToast("Failed")
                    }
                }
            })
        )
	}

    private fun onSuccess(it: Status.Success) {
        viewModel.issueList = it.data as MutableList<Issue>
        setupRecyclerView()
        binding.invalidateAll()
        Log.e("hi", "success")
        progress_bar.visibility = View.GONE
    }

    private fun setupRecyclerView() {
        val issues = viewModel.issueList
        recycler_view.also {
            it.layoutManager = LinearLayoutManager(context)
            issueAdapter =
                IssueAdapter(issues!!, this)
            it.adapter = issueAdapter
        }
    }

    override fun onRecyclerViewItemClick(view: View, obj: Any, position: Int) {
        when(view.id) {
            R.id.issue_card -> {
                seeComments(obj as Issue, position)
            }
        }
    }

    private fun seeComments(issue: Issue, position: Int) {
        viewModel.setSharedData(issue.number!!)
        navController.navigate(R.id.action_issue_detail)
    }

}
