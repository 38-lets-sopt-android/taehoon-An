package com.example.letssopt.ui.signup

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import com.example.letssopt.data.local.PreferenceManager
import com.example.letssopt.data.local.model.AccountDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignUpViewModel(application: Application) : AndroidViewModel(application) {
    data class SignUpUiState(
        val textId : String = "",
        val textPw : String = "",
        val textCkPw : String = ""
    )
    // ViewModel 내부에서만 값을 변화시키도록 하는 독립적인(실질적인) 변수 DataClass = private
    private val _uiState = MutableStateFlow(SignUpUiState())
    // 외부에서 접근할 수 있는 변수, 단 실질적으로 변화시킬 순 없기에 _uiState로 참조해주면서 동시에 asStateFlow() 함수로 잠굼
    // 외부에서 접근하기에 public
    val uiState = _uiState.asStateFlow()
    private val prefManager = PreferenceManager(application)

    fun onChangedId(newId : String) {
       _uiState.update { it.copy(textId = newId) }
    }

    fun onChangedPw(newPw : String) {
        _uiState.update { it.copy(textPw = newPw) }
    }

    fun onChangedCkPw(newCkPw : String) {
        _uiState.update { it.copy(textCkPw = newCkPw) }
    }

    fun validationCheck(textId: String, textPw: String, textPwCheck: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(textId).matches() &&
            textPw.length in 8..12 &&
            textPw == textPwCheck
    }

    fun onSaveAccount(saveId : String, savePw : String) = prefManager.setAccount(AccountDTO(saveId, savePw))


}


