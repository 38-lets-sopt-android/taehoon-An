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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.R
import com.example.letssopt.ui.theme.AsBg
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
            MainTopBar()
        },
        bottomBar = {
            MainBottomBar()
        }
    ) { innerPadding ->
        MainContent(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun MainTopBar() {
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
}

@Composable
fun MainBottomBar() {
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
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.btmbar_main),
                    contentDescription = "mainButton",
                    tint = AsWhite
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "메인",
                    style = MaterialTheme.typography.labelSmall,
                    color = AsWhite
                )
            }
        }
        IconButton(
            onClick = {},
            modifier = Modifier.size(width = 48.dp, height = 50.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.btmbar_category),
                    contentDescription = "categoryButton",
                    tint = AsWhite
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "개별 구매",
                    style = MaterialTheme.typography.labelSmall,
                    color = AsWhite
                )
            }
        }
        IconButton(
            onClick = {},
            modifier = Modifier.size(width = 48.dp, height = 50.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.btmbar_wallet),
                    contentDescription = "walletButton",
                    tint = AsWhite
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "웹툰",
                    style = MaterialTheme.typography.labelSmall,
                    color = AsWhite
                )
            }
        }
        IconButton(
            onClick = {},
            modifier = Modifier.size(width = 48.dp, height = 50.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.btmbar_search),
                    contentDescription = "searchButton",
                    tint = AsWhite
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "찾기",
                    style = MaterialTheme.typography.labelSmall,
                    color = AsWhite
                )
            }
        }
        IconButton(
            onClick = {},
            modifier = Modifier.size(width = 48.dp, height = 50.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.btmbar_folder),
                    contentDescription = "folderButton",
                    tint = AsWhite
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "보관함",
                    style = MaterialTheme.typography.labelSmall,
                    color = AsWhite
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    LETSSOPTTheme {
        MainScreen()
    }
}
