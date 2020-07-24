package com.example.acsassignment.globals

import android.content.Context
import android.widget.Toast
import com.example.acsassignment.manager.MainManager
import com.example.acsassignment.common.network.ApiModule
import retrofit2.Retrofit

class Globals {

    companion object {
        private lateinit var context: Context
        private lateinit var managers: Managers
        private lateinit var apiModule: ApiModule
        private lateinit var retrofit: Retrofit


        fun setup(context: Context) {
            this.context = context
            apiModule = ApiModule()
            retrofit = apiModule.buildRetrofit()
            managers = Managers(
                this.context,
                this.retrofit
            )

            managers.setupManagers()
            // TODO Load From Cookie....If any
        }

        fun context(): Context {
            return context
        }

        fun mainManager(): MainManager {
            return managers.mainManager
        }

        fun showToast(message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}
