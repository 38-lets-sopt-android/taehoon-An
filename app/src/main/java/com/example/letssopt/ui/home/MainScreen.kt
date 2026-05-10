package com.example.letssopt.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.R
import com.example.letssopt.core.local.model.BuyingTabCardItem
import com.example.letssopt.ui.home.contents.BuyingContent
import com.example.letssopt.ui.home.contents.EmptyContent
import com.example.letssopt.ui.home.contents.MainContent
import com.example.letssopt.ui.theme.AsBg
import com.example.letssopt.ui.theme.AsDisable
import com.example.letssopt.ui.theme.AsWhite
import com.example.letssopt.ui.theme.LETSSOPTTheme
import kotlinx.serialization.Serializable

@Serializable
data object Main

@Composable
fun MainRoute(
    viewModel: MainViewModel = viewModel(),
    onNavigateToProfile: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val topBarState by remember {mutableStateOf(false)}

    MainScreen(
        uiState,
        onItemSelected = { newItem -> viewModel.onSelectBottomItem(newItem) },
        onSaveBuyingCardItem = { item -> viewModel.saveBuyingTabCard(item) },
        onProfileClick = { onNavigateToProfile() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    uiState: MainUiState,
    onItemSelected: (SelectBottomItems) -> Unit,
    onSaveBuyingCardItem: (BuyingTabCardItem) -> Unit,
    onProfileClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            if (uiState.selectBottomItem != SelectBottomItems.CATEGORY) {
                MainTopBar(
                    onProfileClick = { onProfileClick() }
                )
            }
        },
        bottomBar = {
            MainBottomBar(
                uiState.selectBottomItem,
                onItemSelected = { item -> onItemSelected(item) }
            )
        }
    ) { innerPadding ->
        when (uiState.selectBottomItem) {
            SelectBottomItems.MAIN -> MainContent(
                modifier = Modifier.padding(innerPadding),
                rowItemList = uiState.rowItemList,
                colItemList = uiState.colItemList,
                lastItemList = uiState.lastItemList,
            )

            SelectBottomItems.CATEGORY -> BuyingContent(
                modifier = Modifier.padding(innerPadding),
                onSaveBuyingCardItem = { item -> onSaveBuyingCardItem(item) },
                gridItemList = uiState.gridItemList
            )

            SelectBottomItems.WALLET -> EmptyContent(
                modifier = Modifier.padding(innerPadding)
            )

            SelectBottomItems.SEARCH -> EmptyContent(
                modifier = Modifier.padding(innerPadding)
            )

            SelectBottomItems.FOLDER -> EmptyContent(
                modifier = Modifier.padding(innerPadding)
            )

            null -> EmptyContent(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun MainTopBar(
    onProfileClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(AsBg)
            .statusBarsPadding()
            .padding(horizontal = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(id = R.drawable.btn_camera),
                contentDescription = null,
                tint = AsWhite
            )
        }
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(id = R.drawable.btn_ring),
                contentDescription = null,
                tint = AsWhite
            )
        }
        IconButton(onClick = { onProfileClick() }) {
            Icon(
                painter = painterResource(id = R.drawable.btn_profile),
                contentDescription = null,
                tint = AsWhite
            )
        }
    }
}

@Composable
fun MainBottomBar(
    selectBottomItem: SelectBottomItems?,
    onItemSelected: (SelectBottomItems) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding() // NavigationBarsPadding 메서드 순서 중요(height이후에 지정해주면 제대로 먹지 않음)
            .height(72.dp)
            .background(AsBg)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BottomTabItem(
            item = SelectBottomItems.MAIN,
            label = "메인",
            iconRes = R.drawable.btmbar_main,
            isSelected = selectBottomItem == SelectBottomItems.MAIN,
            onItemSelected = onItemSelected
        )
        BottomTabItem(
            item = SelectBottomItems.CATEGORY,
            label = "개별구매",
            iconRes = R.drawable.btmbar_category,
            isSelected = selectBottomItem == SelectBottomItems.CATEGORY,
            onItemSelected = onItemSelected
        )
        BottomTabItem(
            item = SelectBottomItems.WALLET,
            label = "웹툰",
            iconRes = R.drawable.btmbar_wallet,
            isSelected = selectBottomItem == SelectBottomItems.WALLET,
            onItemSelected = onItemSelected
        )
        BottomTabItem(
            item = SelectBottomItems.SEARCH,
            label = "찾기",
            iconRes = R.drawable.btmbar_search,
            isSelected = selectBottomItem == SelectBottomItems.SEARCH,
            onItemSelected = onItemSelected
        )
        BottomTabItem(
            item = SelectBottomItems.FOLDER,
            label = "보관함",
            iconRes = R.drawable.btmbar_folder,
            isSelected = selectBottomItem == SelectBottomItems.FOLDER,
            onItemSelected = onItemSelected
        )
    }
}

@Composable
fun BottomTabItem(
    item: SelectBottomItems,
    label: String,
    iconRes: Int,
    isSelected: Boolean,
    onItemSelected: (SelectBottomItems) -> Unit
) {
    Column(
        modifier = Modifier
            .width(56.dp)
            .height(60.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { onItemSelected(item) }
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            tint = if (isSelected) AsWhite else AsDisable
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = if (isSelected) AsWhite else AsDisable
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MainContentPreview() {
    LETSSOPTTheme {
        MainScreen(
            uiState = MainUiState(
                selectBottomItem = SelectBottomItems.MAIN
            ),
            onItemSelected = {},
            onSaveBuyingCardItem = {},
            onProfileClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryContentPreview() {
    LETSSOPTTheme {
        MainScreen(
            uiState = MainUiState(
                selectBottomItem = SelectBottomItems.CATEGORY
            ),
            onItemSelected = {},
            onSaveBuyingCardItem = {},
            onProfileClick = {}
        )
    }
}

