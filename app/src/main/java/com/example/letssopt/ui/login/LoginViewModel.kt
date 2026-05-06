package com.example.letssopt.ui.login

import android.app.Application
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
        val account = prefManager.getAccount()
        val currentId = _uiState.value.textId
        val currentPw = _uiState.value.textPw

        viewModelScope.launch {
            if (account.accountId == null || account.accountPw == null) {
                _sideEffect.emit(LoginSideEffect.ShowToast("회원가입이 필요합니다.", Toast.LENGTH_LONG))
                return@launch
            }
            if (account.accountId != currentId) {
                _sideEffect.emit(LoginSideEffect.ShowToast("존재하지 않는 계정입니다.", Toast.LENGTH_SHORT))
                return@launch
            }
            if (account.accountPw != currentPw) {
                _sideEffect.emit(LoginSideEffect.ShowToast("비밀번호가 틀렸습니다. 다시 확인해주세요.", Toast.LENGTH_SHORT))
                return@launch
            }

            prefManager.setIsLoggedIn(true)

            _sideEffect.emit(LoginSideEffect.ShowToast("로그인 성공. 환영합니다. ${account.accountId} 님.", Toast.LENGTH_SHORT))
            _sideEffect.emit(LoginSideEffect.NavigateToMain)
        }
    }



}


