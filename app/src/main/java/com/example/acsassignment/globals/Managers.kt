package com.example.acsassignment.globals

import android.content.Context
import com.example.acsassignment.manager.MainManager
import com.example.acsassignment.common.service.MainService
import com.example.acsassignment.manager.StoreManager
import retrofit2.Retrofit

class Managers(var context: Context, var retrofit: Retrofit) {
    var storeManager: StoreManager = StoreManager(context)

    lateinit var mainManager: MainManager

    fun setupManagers() {
        mainManager =
            MainManager(retrofit.create(MainService::class.java), storeManager)
    }

}