package com.example.letssopt.ui.signup

import android.app.Application
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.data.local.PreferenceManager
import com.example.letssopt.data.local.model.AccountItem
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel(application: Application) : AndroidViewModel(application) {
    // SideEffect
    private val _sideEffect = MutableSharedFlow<SignUpSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

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

    fun onChangedName(newName: String) {
        _uiState.update { it.copy(textName = newName) }
    }

    fun onChangedEmail(newEmail: String) {
        _uiState.update { it.copy(textEmail = newEmail) }
    }

    fun onChangedAge(newAge: String) {
        _uiState.update { it.copy(textAge = newAge) }
    }

    fun onChangedPart(newPart: String) {
        _uiState.update { it.copy(textPart = newPart) }
    }

    fun validationCheck(textId: String, textPw: String, textPwCheck: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(textId).matches() &&
            textPw.length in 8..12 &&
            textPw == textPwCheck
    }

    private fun onSaveAccount(saveId : String, savePw : String) = prefManager.setAccount(AccountItem(saveId, savePw))

    fun validateSignUp() {
        val textId = _uiState.value.textId
        val textPw = _uiState.value.textPw
        val textPwCheck = _uiState.value.textCkPw

        viewModelScope.launch {
            if (validationCheck(textId, textPw, textPwCheck)) {
                onSaveAccount(textId, textPw)
                _sideEffect.emit(SignUpSideEffect.ShowToast("회원가입 성공!", Toast.LENGTH_SHORT))
                _sideEffect.emit(SignUpSideEffect.CompleteSignUp)
            } else {
                _sideEffect.emit(SignUpSideEffect.ShowSnack("회원가입에 실패했습니다. 올바른 정보를 입력해주세요."))
            }
        }

    }

}


