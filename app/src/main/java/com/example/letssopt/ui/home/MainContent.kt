package com.example.letssopt.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.letssopt.R
import com.example.letssopt.ui.theme.AsBg
import com.example.letssopt.ui.theme.AsDisable
import com.example.letssopt.ui.theme.AsSecondaryText
import com.example.letssopt.ui.theme.AsWhite
import com.example.letssopt.ui.theme.LETSSOPTTheme

@Composable
fun MainContent(modifier: Modifier = Modifier, uiState: MainViewModel.MainUiState) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = AsBg)
            .verticalScroll(state = scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            modifier = Modifier
                .padding(top = 24.dp, start = 19.dp)
                .align(Alignment.Start),
            style = MaterialTheme.typography.titleMedium.copy(color = AsWhite),
            text = "방금 막 도착한 신상 컨텐츠"
        )
        Text(
            modifier = Modifier
                .padding(top = 6.dp, start = 19.dp)
                .align(Alignment.Start),
            style = MaterialTheme.typography.bodyMedium.copy(color = AsSecondaryText, fontSize = 18.sp),
            text = "예능부터 드라마까지!"
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(horizontal = 20.dp)
        ) {
            items(uiState.rowItemList) { item ->
                Image(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .size(280.dp, 160.dp)
                        .fillMaxSize()
                        .background(AsDisable),
                    painter = painterResource(id = item),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                    )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun MainContentPreview() {
    LETSSOPTTheme {
        MainContent(uiState = MainViewModel.MainUiState(
            selectBottomItem = MainViewModel.SelectBottomItems.MAIN,
            rowItemList = listOf(
                R.drawable.rowmjg1,
                R.drawable.rowmjg2,
                R.drawable.rowmjg3
            )
        ))
    }
}
