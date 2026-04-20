package com.example.letssopt.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    enum class SelectBottomItems(tag: String) {
        MAIN("메인"),
        CATEGORY("개별구매"),
        WALLET("웹툰"),
        SEARCH("찾기"),
        FOLDER("보관함")
    }

    data class MainUiState(
        val selectBottomItem: SelectBottomItems?
    )

    private val _uiState = MutableStateFlow(MainUiState(SelectBottomItems.MAIN))
    val uiState = _uiState.asStateFlow()

    fun onSelectBottomItem(selectBottomItem: SelectBottomItems?) {
        _uiState.value = _uiState.value.copy(selectBottomItem = selectBottomItem)
    }

}
