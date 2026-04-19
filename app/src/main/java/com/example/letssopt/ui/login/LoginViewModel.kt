package com.example.letssopt.ui.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {
    data class LoginUiState(
        val textId : String = "",
        val textPw : String = ""
    )
    // ViewModel 내부에서만 값을 변화시키도록 하는 독립적인(실질적인) 변수 DataClass = private
    private val _uiState = MutableStateFlow(LoginUiState())
    // 외부에서 접근할 수 있는 변수, 단 실질적으로 변화시킬 순 없기에 _uiState로 참조해주면서 동시에 asStateFlow() 함수로 잠굼
    // 외부에서 접근하기에 public
    val uiState = _uiState.asStateFlow()

    fun onChangedId(newId : String) {
       _uiState.update { it.copy(textId = newId) }
    }

    fun onChangedPw(newPw : String) {
        _uiState.update { it.copy(textPw = newPw) }
    }
}


