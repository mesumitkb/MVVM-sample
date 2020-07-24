package com.example.acsassignment.globals

import android.app.Application

class CustomApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Globals.setup(applicationContext)
    }
}
