package com.example.acsassignment.common

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.acsassignment.common.utils.DefaultUIObserver
import kotlinx.android.synthetic.main.fragment_issue_list.*

open class BaseFragment : Fragment()  {

    lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    fun setObserver(liveData: LiveData<Any>, observer: Observer<Any>?= null, owner: LifecycleOwner?= this) {
        liveData.observe(owner!!,
            DefaultUIObserver(
                observer,
                progress_bar
            )
        )
    }
}
