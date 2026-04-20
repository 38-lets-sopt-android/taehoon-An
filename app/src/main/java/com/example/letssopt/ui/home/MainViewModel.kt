package com.example.letssopt.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    enum class SelectBottomItems(number: Int) {
        MAIN(0),
        CATEGORY(1),
        WALLET(2),
        SEARCH(3),
        FOLDER(4)
    }

    data class MainUiState(
        val selectBottomItem: SelectBottomItems = SelectBottomItems.MAIN
    )

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()

    fun onSelectBottomItem(selectBottomItem: SelectBottomItems) {
        _uiState.value = _uiState.value.copy(selectBottomItem = selectBottomItem)
    }

}
