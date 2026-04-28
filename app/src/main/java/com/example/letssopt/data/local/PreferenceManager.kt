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

    companion object {
        private const val PREF_NAME = "user_data"
        private const val KEY_ID = "id"
        private const val KEY_PW = "pw"
    }
}
