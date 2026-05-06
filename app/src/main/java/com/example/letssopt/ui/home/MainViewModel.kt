package com.example.letssopt.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.data.local.BuyingItem
import com.example.letssopt.data.local.BuyingItemDao
import com.example.letssopt.data.local.model.BuyingTabCardItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(private val dao: BuyingItemDao) : ViewModel() {
    enum class SelectBottomItems(val tag: String) {
        MAIN("메인"),
        CATEGORY("개별구매"),
        WALLET("웹툰"),
        SEARCH("찾기"),
        FOLDER("보관함")
    }

    val items = dao.getAllBuyingItems()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _uiState = MutableStateFlow(MainUiState(SelectBottomItems.MAIN))
    val uiState = _uiState.asStateFlow()

    fun onSelectBottomItem(selectBottomItem: SelectBottomItems) {
        _uiState.value = _uiState.value.copy(selectBottomItem = selectBottomItem)
    }

    fun saveBuyingTabCard(item : BuyingTabCardItem) {
        viewModelScope.launch {
            dao.insert(BuyingItem(image = item.image, title = item.text))
        }
    }

}
