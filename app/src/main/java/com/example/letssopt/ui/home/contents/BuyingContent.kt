package com.example.letssopt.ui.home.contents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.R
import com.example.letssopt.core.local.model.BuyingTabCardItem
import com.example.letssopt.ui.components.BuyingTabCard
import com.example.letssopt.ui.theme.AsBg
import com.example.letssopt.ui.theme.AsWhite
import com.example.letssopt.ui.theme.LETSSOPTTheme

@Composable
fun BuyingContent(
    modifier: Modifier = Modifier,
    onSaveBuyingCardItem: (BuyingTabCardItem) -> Unit,
    gridItemList: List<BuyingTabCardItem>
) {
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .background(color = AsBg),
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            Text(
                modifier = Modifier
                    .padding(top = 24.dp, start = 8.dp, bottom = 30.dp),
                style = MaterialTheme.typography.titleMedium.copy(
                    color = AsWhite,
                    fontWeight = Bold
                ),
                text = "개별 구매"
            )
        }
        items(gridItemList) { item ->
            BuyingTabCard(
                item = item,
                onClick = { onSaveBuyingCardItem(item) }
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun BuyingContentPreview() {
    LETSSOPTTheme {
        BuyingContent(
            modifier = Modifier,
            gridItemList = listOf(
                BuyingTabCardItem(R.drawable.colmjg1, "먼작귀 101화"),
                BuyingTabCardItem(R.drawable.colmjg1, "먼작귀 101화"),
                BuyingTabCardItem(R.drawable.colmjg1, "먼작귀 101화"),
                BuyingTabCardItem(R.drawable.colmjg1, "먼작귀 101화"),
                BuyingTabCardItem(R.drawable.colmjg1, "먼작귀 101화"),
            ),
            onSaveBuyingCardItem = {}
        )
    }
}
