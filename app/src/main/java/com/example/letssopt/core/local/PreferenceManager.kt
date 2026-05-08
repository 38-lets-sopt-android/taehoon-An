package com.example.letssopt.core.local

import android.content.Context
import com.example.letssopt.core.local.model.AccountItem

class PreferenceManager(context: Context) {
    private val sharedPreference = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun setId(id: Int) {
        sharedPreference.edit()
            .putInt(KEY_ID, id)
            .apply()
    }

    fun getId(): Int { return sharedPreference.getInt(KEY_ID, -1) }

    fun setIsLoggedIn(login: Boolean) {
        sharedPreference.edit()
            .putBoolean(KEY_IS_LOGGED_IN, login)
            .apply()
    }

    fun getIsLoggedIn(): Boolean {
        return sharedPreference.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    companion object {
        private const val PREF_NAME = "user_data"
        private const val KEY_ID = "id"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }
}
