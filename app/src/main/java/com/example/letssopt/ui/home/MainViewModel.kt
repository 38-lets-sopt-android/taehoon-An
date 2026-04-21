package com.example.letssopt.ui.home

import androidx.lifecycle.ViewModel
import com.example.letssopt.R
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
        val selectBottomItem: SelectBottomItems?,
        val rowItemList: List<Int> = listOf(
            R.drawable.rowmjg1,
            R.drawable.rowmjg2,
            R.drawable.rowmjg3
        ),
        val colItemList: List<Int> = listOf(
            R.drawable.colmjg1,
            R.drawable.colmjg2,
            R.drawable.colmjg3,
            R.drawable.colmjg4
        )
    )

    private val _uiState = MutableStateFlow(MainUiState(SelectBottomItems.MAIN))
    val uiState = _uiState.asStateFlow()

    fun onSelectBottomItem(selectBottomItem: SelectBottomItems?) {
        _uiState.value = _uiState.value.copy(selectBottomItem = selectBottomItem)
    }

}
