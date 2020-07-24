package com.example.acsassignment.manager

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.GsonBuilder

class StoreManager(private val context: Context) {

    fun sharedPreference(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun <T> saveObject(`object`: T, key: String) {
        val editor = sharedPreference().edit()
        val jsonString = GsonBuilder().create().toJson(`object`)
        editor.putString(key, jsonString).apply()
    }

    inline fun <reified T> getObject(key: String, defValue: String? = null): T? {
        val value = sharedPreference().getString(key, defValue)
        return GsonBuilder().create().fromJson(value, T::class.java)
    }

}