package com.example.letssopt.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.letssopt.ui.home.MainViewModel
import com.example.letssopt.ui.theme.AsDisable

@Composable
fun InfiniteLazyRow(uiState: MainViewModel.MainUiState) {
    val itemsList = uiState.rowItemList

    val startIndex = Int.MAX_VALUE / 2 - (Int.MAX_VALUE / 2 % itemsList.size)
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = startIndex)

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        state = listState,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(Int.MAX_VALUE) { index ->
            val realIndex = index % itemsList.size
            val item = itemsList[realIndex]
            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .size(280.dp, 160.dp)
                    .background(AsDisable),
                painter = painterResource(id = item),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
    }
}
