package com.example.letssopt.ui.signup

import android.app.Application
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.core.local.PreferenceManager
import com.example.letssopt.core.local.model.AccountItem
import com.example.letssopt.core.local.retrofit.RetrofitClient
import com.example.letssopt.core.local.retrofit.SignUpRequest
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

    private fun validationCheck(textId: String, textPw: String, textPwCheck: String, textEmail: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(textId).matches() &&
            textPw.length in 8..12 &&
            textPw == textPwCheck &&
                Patterns.EMAIL_ADDRESS.matcher(textEmail).matches()
    }

    private fun onSaveAccount(saveId : String, savePw : String) = prefManager.setAccount(AccountItem(saveId, savePw))

    fun validateSignUp() {
        val textId = _uiState.value.textId
        val textPw = _uiState.value.textPw
        val textCkPw = _uiState.value.textCkPw
        val textName = _uiState.value.textName
        val textEmail = _uiState.value.textEmail
        val textAge = _uiState.value.textAge
        val textPart = _uiState.value.textPart

        if(!validationCheck(textId, textPw, textCkPw, textEmail)) {
            val message = "회원가입 조건에 부합하지 않습니다. 다시 시도해주세요."
            viewModelScope.launch { _sideEffect.emit(SignUpSideEffect.ShowSnack(message)) }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(signUpStatus = SignUpStatus.Loading) }

            runCatching {
                RetrofitClient.apiService.signUp(
                    SignUpRequest(textId, textPw, textName, textEmail, textAge.toIntOrNull() ?: -1, textPart)
                )
            }.onSuccess { response ->
                if (response.isSuccessful) {
                    _uiState.update { it.copy(signUpStatus = SignUpStatus.Idle) }

                    onSaveAccount(textId, textPw)
                    _sideEffect.emit(SignUpSideEffect.ShowToast("회원가입 성공!", Toast.LENGTH_SHORT))
                    _sideEffect.emit(SignUpSideEffect.CompleteSignUp)
                } else {
                    _uiState.update { it.copy(signUpStatus = SignUpStatus.Idle) }

                    val message = "회원가입 실패 (코드: ${response.code()})"
                    _sideEffect.emit(SignUpSideEffect.ShowSnack(message))
                }
            }.onFailure { e ->
                _uiState.update { it.copy(signUpStatus = SignUpStatus.Idle) }

                val errorMessage = e.message ?: "네트워크 오류가 발생했습니다"
                _sideEffect.emit(SignUpSideEffect.ShowSnack(errorMessage))
            }
        }

    }

}


