package com.example.letssopt.ui.home.profile

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.core.local.PreferenceManager
import com.example.letssopt.core.local.model.ProfileContentItem
import com.example.letssopt.core.local.retrofit.RetrofitClient
import com.example.letssopt.ui.util.EventStatus
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(prefManager: PreferenceManager) : ViewModel() {
    private val _uiState = MutableStateFlow(
        ProfileUiState(
            profileContent = ProfileContentItem(
                textId = null,
                textName = null,
                textEmail = null,
                textAge = null,
                textPart = null
            ),
            profileStatus = EventStatus.Loading
        )
    )
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<ProfileSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    val prefManager = prefManager

    init { getUsersInfo() }


    private fun getUsersInfo() {
        viewModelScope.launch {
            runCatching {
                RetrofitClient.apiService.getUserInfo(
                    userId = prefManager.getId()
                )
            }.onSuccess { response ->
                if (response.isSuccessful) {
                    _uiState.update { it.copy(profileStatus = EventStatus.Idle) }

                    _uiState.update {
                        it.copy(
                            profileContent = ProfileContentItem(
                                textId = response.body()?.data?.loginId,
                                textName = response.body()?.data?.name,
                                textEmail = response.body()?.data?.email,
                                textAge = response.body()?.data?.age,
                                textPart = response.body()?.data?.part
                            )
                        )
                    }
                } else {
                    _uiState.update { it.copy(profileStatus = EventStatus.Idle) }

                    val message = "사용자를 찾을 수 없습니다. (코드: ${response.code()})"
                    _sideEffect.emit(ProfileSideEffect.ShowToast(message, Toast.LENGTH_SHORT))
                }
            }.onFailure { e ->
                _uiState.update { it.copy(profileStatus = EventStatus.Idle) }

                val errorMessage = e.message ?: "네트워크 오류가 발생했습니다"
                _sideEffect.emit(ProfileSideEffect.ShowToast(errorMessage, Toast.LENGTH_SHORT))
            }
        }
    }
}
