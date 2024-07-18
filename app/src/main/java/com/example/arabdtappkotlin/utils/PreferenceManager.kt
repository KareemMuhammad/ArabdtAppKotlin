package com.example.arabdtappkotlin.utils

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {

    lateinit var instance : PreferencesManager
    companion object{
        val ONBOARDING_KEY : String = "isOnboarded"
    }
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)

    fun saveBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

}