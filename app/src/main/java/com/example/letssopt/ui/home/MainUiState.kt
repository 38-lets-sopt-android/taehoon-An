package com.example.letssopt.ui.home

import com.example.letssopt.R
import com.example.letssopt.data.local.model.BuyingTabCardItem
import com.example.letssopt.data.local.model.WatchPartyItem

data class MainUiState(
        val selectBottomItem: SelectBottomItems,
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
        ),
        val gridItemList: List<BuyingTabCardItem> = listOf(
            BuyingTabCardItem(R.drawable.colmjg1, "먼작귀 시즌 106화"),
            BuyingTabCardItem(R.drawable.colmjg2, "먼작귀 시즌 11화"),
            BuyingTabCardItem(R.drawable.colmjg3, "먼작귀 시즌 36화"),
            BuyingTabCardItem(R.drawable.colmjg4, "먼작귀 시즌 58화"),
        )
    )
