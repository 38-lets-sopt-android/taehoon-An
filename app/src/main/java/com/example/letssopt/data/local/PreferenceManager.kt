package com.example.letssopt.data.local

import android.content.Context
import com.example.letssopt.data.local.model.AccountItem

class PreferenceManager(context : Context) {
    private val sharedPreference = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun setAccount(account: AccountItem) {
        sharedPreference.edit()
            .putString(KEY_ID, account.accountId)
            .putString(KEY_PW, account.accountPw)
            .apply()
    }

    fun getAccount() : AccountItem {
        return AccountItem(
            accountId = sharedPreference.getString(KEY_ID, "") ?: "",
            accountPw = sharedPreference.getString(KEY_PW, "") ?: ""
        )
    }

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
        private const val KEY_ID = "id"
        private const val KEY_PW = "pw"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }
}
