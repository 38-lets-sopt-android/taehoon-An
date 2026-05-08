package com.example.letssopt.ui.login

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.core.local.PreferenceManager
import com.example.letssopt.core.local.retrofit.LoginRequest
import com.example.letssopt.core.local.retrofit.RetrofitClient
import com.example.letssopt.ui.util.EventStatus
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    // SideEffect
    private val _sideEffect = MutableSharedFlow<LoginSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    // uiState
    // ViewModel 내부에서만 값을 변화시키도록 하는 독립적인(실질적인) 변수 DataClass = private
    private val _uiState = MutableStateFlow(LoginUiState())
    // 외부에서 접근할 수 있는 변수, 단 실질적으로 변화시킬 순 없기에 _uiState로 참조해주면서 동시에 asStateFlow() 함수로 잠굼
    // 외부에서 접근하기에 public
    val uiState = _uiState.asStateFlow()
    private val prefManager = PreferenceManager(application)


    // functions
    fun onChangedId(newId : String) {
       _uiState.update { it.copy(textId = newId) }
    }

    fun onChangedPw(newPw : String) {
        _uiState.update { it.copy(textPw = newPw) }
    }

    fun getIsLoggedIn() : Boolean {
        return prefManager.getIsLoggedIn()
    }

    fun validateLogin() {
        val currentId = _uiState.value.textId
        val currentPw = _uiState.value.textPw

        viewModelScope.launch {
            _uiState.update { it.copy(loginStatus = EventStatus.Loading) }

            runCatching {
                RetrofitClient.apiService.signIn(
                    LoginRequest(
                        currentId,
                        currentPw
                    )
                )
            }.onSuccess { response ->
                if (response.isSuccessful) {
                    _uiState.update { it.copy(loginStatus = EventStatus.Idle) }

                    prefManager.setIsLoggedIn(true)
                    _sideEffect.emit(LoginSideEffect.ShowToast("로그인 성공!", Toast.LENGTH_SHORT))
                    _sideEffect.emit(LoginSideEffect.NavigateToMain)
                } else {
                    _uiState.update { it.copy(loginStatus = EventStatus.Idle) }

                    val message = "로그인 실패 (코드: ${response.code()})"
                    _sideEffect.emit(LoginSideEffect.ShowToast(message, Toast.LENGTH_SHORT))
                }
            }.onFailure { e ->
                _uiState.update { it.copy(loginStatus = EventStatus.Idle) }

                val errorMessage = e.message ?: "네트워크 오류가 발생했습니다"
                _sideEffect.emit(LoginSideEffect.ShowToast(errorMessage, Toast.LENGTH_SHORT))
            }
        }
    }


}


