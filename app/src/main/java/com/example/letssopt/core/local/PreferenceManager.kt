package com.example.letssopt.core.local

import android.content.Context
import com.example.letssopt.core.local.model.AccountItem

class PreferenceManager(context : Context) {
    private val sharedPreference = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun setIsLoggedIn(login : Boolean) {
        sharedPreference.edit()
            .putBoolean(KEY_IS_LOGGED_IN, login)
            .apply()
    }

    fun getIsLoggedIn() : Boolean {
        return sharedPreference.getBoolean(KEY_IS_LOGGED_IN, false) ?: false
    }

    companion object {
        private const val PREF_NAME = "user_data"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }
}
