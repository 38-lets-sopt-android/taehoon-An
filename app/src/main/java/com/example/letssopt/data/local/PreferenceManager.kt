package com.example.letssopt.data.local

import android.content.Context
import com.example.letssopt.data.local.model.AccountDTO

class PreferenceManager(context : Context) {
    private val sharedPreference = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)

    fun setAccount(account : AccountDTO) {
        sharedPreference.edit()
            .putString("id", account.accountId)
            .putString("pw", account.accountPw)
            .apply()
    }

    fun getAccount() : AccountDTO {
        return AccountDTO(sharedPreference.getString("id", ""), sharedPreference.getString("pw", ""))
    }
}
