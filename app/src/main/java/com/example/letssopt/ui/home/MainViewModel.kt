package com.example.letssopt.ui.home

import androidx.lifecycle.ViewModel
import com.example.letssopt.R
import com.example.letssopt.data.local.model.WatchPartyItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    enum class SelectBottomItems(val tag: String) {
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
        ),
        val lastItemList: List<WatchPartyItem> = listOf(
            WatchPartyItem(R.drawable.lastimg1, "오늘 22:15분에 시작", listOf("먼작귀", "치이카와")),
            WatchPartyItem(R.drawable.lastimg2, "내일 07:45분에 시작", listOf("먼작귀", "치이카와")),
            WatchPartyItem(R.drawable.lastimg3, "내일 13:30분에 시작", listOf("먼작귀", "울보"))
        )
    )

    private val _uiState = MutableStateFlow(MainUiState(SelectBottomItems.MAIN))
    val uiState = _uiState.asStateFlow()

    fun onSelectBottomItem(selectBottomItem: SelectBottomItems) {
        _uiState.value = _uiState.value.copy(selectBottomItem = selectBottomItem)
    }

}
