package com.example.letssopt.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.letssopt.R
import com.example.letssopt.data.local.model.WatchPartyItem
import com.example.letssopt.ui.components.InfiniteLazyRow
import com.example.letssopt.ui.components.WatchaPartyComponent
import com.example.letssopt.ui.theme.AsBg
import com.example.letssopt.ui.theme.AsDisable
import com.example.letssopt.ui.theme.AsSecondaryText
import com.example.letssopt.ui.theme.AsWhite
import com.example.letssopt.ui.theme.LETSSOPTTheme

@Composable
fun MainContent(modifier: Modifier = Modifier, uiState: MainUiState) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = AsBg),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier
                        .padding(top = 24.dp, start = 19.dp),
                    style = MaterialTheme.typography.titleMedium.copy(color = AsWhite, fontWeight = Bold),
                    text = "방금 막 도착한 신상 컨텐츠"
                )
                Text(
                    modifier = Modifier
                        .padding(start = 19.dp),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = AsSecondaryText,
                        fontSize = 18.sp,
                        fontWeight = Bold
                    ),
                    text = "예능부터 드라마까지!"
                )
            }
        }

        item {
            Spacer(Modifier.padding(top = 24.dp))

            InfiniteLazyRow(uiState)
        }

        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Image(
                    modifier = Modifier
                        .padding(top = 26.dp, start = 16.dp),
                    painter = painterResource(id = R.drawable.ic_watgorithm),
                    contentDescription = null
                )
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "예능부터 드라마까지!",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = AsSecondaryText,
                        fontSize = 18.sp,
                        fontWeight = Bold
                    )
                )

                Text(
                    modifier = Modifier.clickable { },
                    text = "더보기",
                    style = MaterialTheme.typography.labelSmall.copy(color = AsSecondaryText)
                )
            }
        }

        item {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                items(uiState.colItemList) { item ->
                    Image(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .size(100.dp, 150.dp)
                            .background(AsDisable),
                        painter = painterResource(id = item),
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )
                }
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 26.dp, bottom = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "공개 예정 컨텐츠",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = AsWhite,
                        fontSize = 18.sp,
                        fontWeight = Bold
                    )
                )

                Text(
                    modifier = Modifier.clickable { },
                    text = "더보기",
                    style = MaterialTheme.typography.labelSmall.copy(color = AsSecondaryText)
                )
            }
        }

        item {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                items(uiState.colItemList) { item ->
                    Image(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .size(100.dp, 150.dp)
                            .background(AsDisable),
                        painter = painterResource(id = item),
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )
                }
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 26.dp, bottom = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "왓챠 파티",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = AsWhite,
                        fontSize = 18.sp,
                        fontWeight = Bold
                    )
                )

                Text(
                    modifier = Modifier.clickable { },
                    text = "더보기",
                    style = MaterialTheme.typography.labelSmall.copy(color = AsSecondaryText)
                )
            }
        }

        item {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                items(uiState.lastItemList) { item ->
                    WatchaPartyComponent(item = item, onClick = {})
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainContentPreview() {
    LETSSOPTTheme {
        MainContent(
            uiState = MainUiState(
                selectBottomItem = SelectBottomItems.MAIN,
                rowItemList = listOf(
                    R.drawable.rowmjg1,
                    R.drawable.rowmjg2,
                    R.drawable.rowmjg3
                ),
                colItemList = listOf(
                    R.drawable.colmjg1,
                    R.drawable.colmjg2,
                    R.drawable.colmjg3,
                    R.drawable.colmjg4
                ),
                lastItemList = listOf(
                    WatchPartyItem(
                        R.drawable.lastimg1,
                        "오늘 22:15분에 시작",
                        listOf("먼작귀", "치이카와", "울보")
                    ),
                    WatchPartyItem(
                        R.drawable.lastimg2,
                        "내일 07:45분에 시작",
                        listOf("이것도", "먼작귀", "치이카와")
                    ),
                    WatchPartyItem(
                        R.drawable.lastimg3,
                        "내일 13:30분에 시작",
                        listOf("먼작귀", "치이카와", "울보")
                    )
                )
            )
        )
    }
}
