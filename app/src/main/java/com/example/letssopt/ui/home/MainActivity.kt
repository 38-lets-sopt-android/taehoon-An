package com.example.letssopt.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.R
import com.example.letssopt.ui.theme.AsBg
import com.example.letssopt.ui.theme.AsSecondaryText
import com.example.letssopt.ui.theme.AsWhite
import com.example.letssopt.ui.theme.LETSSOPTTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LETSSOPTTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
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
                        contentDescription = "camera",
                        tint = AsWhite
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.btn_ring),
                        contentDescription = "ring",
                        tint = AsWhite
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.btn_profile),
                        contentDescription = "profile",
                        tint = AsWhite
                    )
                }
            }
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp)
                    .background(AsBg)
                    .navigationBarsPadding()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {},
                    modifier = Modifier.size(width = 48.dp, height = 50.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally, // 가로 중앙 정렬
                        verticalArrangement = Arrangement.Center           // 세로 중앙 정렬
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.btmbar_main),
                            contentDescription = "mainButton",
                            tint = AsWhite
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "메인",
                            style = MaterialTheme.typography.labelSmall, // 작은 글씨 스타일
                            color = AsWhite
                        )
                    }
                }
                IconButton(
                    onClick = {},
                    modifier = Modifier.size(width = 48.dp, height = 50.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally, // 가로 중앙 정렬
                        verticalArrangement = Arrangement.Center           // 세로 중앙 정렬
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.btmbar_category),
                            contentDescription = "categoryButton",
                            tint = AsWhite
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "개별 구매",
                            style = MaterialTheme.typography.labelSmall, // 작은 글씨 스타일
                            color = AsWhite
                        )
                    }
                }
                IconButton(
                    onClick = {},
                    modifier = Modifier.size(width = 48.dp, height = 50.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally, // 가로 중앙 정렬
                        verticalArrangement = Arrangement.Center           // 세로 중앙 정렬
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.btmbar_wallet),
                            contentDescription = "walletButton",
                            tint = AsWhite
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "웹툰",
                            style = MaterialTheme.typography.labelSmall, // 작은 글씨 스타일
                            color = AsWhite
                        )
                    }
                }
                IconButton(
                    onClick = {},
                    modifier = Modifier.size(width = 48.dp, height = 50.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally, // 가로 중앙 정렬
                        verticalArrangement = Arrangement.Center           // 세로 중앙 정렬
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.btmbar_search),
                            contentDescription = "searchButton",
                            tint = AsWhite
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "찾기",
                            style = MaterialTheme.typography.labelSmall, // 작은 글씨 스타일
                            color = AsWhite
                        )
                    }
                }
                IconButton(
                    onClick = {},
                    modifier = Modifier.size(width = 48.dp, height = 50.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally, // 가로 중앙 정렬
                        verticalArrangement = Arrangement.Center           // 세로 중앙 정렬
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.btmbar_folder),
                            contentDescription = "folderButton",
                            tint = AsWhite
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "보관함",
                            style = MaterialTheme.typography.labelSmall, // 작은 글씨 스타일
                            color = AsWhite
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        // 2. Scaffold가 제공하는 padding을 MainContent에 전달
        MainContent(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = AsBg),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(
            style = MaterialTheme.typography.titleLarge.copy(color = AsSecondaryText),
            text = "로그인을 축하합니다~~"
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun MainContentPreview() {
    LETSSOPTTheme {
        MainScreen()
    }
}
