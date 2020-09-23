package com.georgcantor.firebaseauth.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.georgcantor.firebaseauth.data.User
import com.georgcantor.firebaseauth.util.Constants.MAIN_STORAGE
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PreferenceManager(context: Context) {

    private val gson = Gson()

    private val prefs: SharedPreferences = context.getSharedPreferences(MAIN_STORAGE, MODE_PRIVATE)

    fun saveBoolean(key: String, value: Boolean) = prefs.edit().putBoolean(key, value).apply()

    fun getBoolean(key: String): Boolean = prefs.getBoolean(key, false)

    fun saveUser(key: String, user: User) {
        val json = gson.toJson(user)
        prefs.edit().putString(key, json).apply()
    }

    fun getUser(key: String): User? {
        val type = object : TypeToken<User>() {}.type
        val json = prefs.getString(key, "")

        return gson.fromJson(json, type)
    }
}