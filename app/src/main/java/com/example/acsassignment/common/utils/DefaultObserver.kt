package com.example.acsassignment.common.utils

import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import com.example.acsassignment.common.Status

open class DefaultObserver<T>(open var observer: Observer<T>? = null): Observer<T> {
    override fun onChanged(t: T) {
        observer?.onChanged(t)
    }
}

class DefaultUIObserver<T>(override var observer: Observer<T>? = null, var progressBar: ProgressBar?= null): DefaultObserver<T>(observer) {
    override fun onChanged(t: T) {
        when (t) {
            is Status.Loading -> {
                progressBar?.visibility = View.VISIBLE
            }
            is Status.Success -> {
                progressBar?.visibility = View.GONE
            }
            is Status.Failure -> {
                progressBar?.visibility = View.GONE
            }
        }
        super.onChanged(t)
    }
}
