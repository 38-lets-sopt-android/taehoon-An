package com.example.letssopt.ui.home.profile

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.core.local.model.ProfileContentItem
import com.example.letssopt.ui.components.DefaultButton
import com.example.letssopt.ui.theme.AsBg
import com.example.letssopt.ui.theme.AsSecondaryText
import com.example.letssopt.ui.theme.AsWhite
import com.example.letssopt.ui.theme.LETSSOPTTheme
import com.example.letssopt.ui.util.EventStatus
import kotlinx.serialization.Serializable

@Serializable
data object Profile

@Composable
fun ProfileRoute(
    viewModel: ProfileViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is ProfileSideEffect.ShowToast -> {
                    Toast.makeText(context, effect.message, effect.duration).show()
                }
            }
        }
    }

    ProfileScreen(
        uiState = uiState
    )
}

@Composable
fun ProfileScreen(
    uiState: ProfileUiState,
    onSeeingOtherUsers: () -> Unit = {}
) {
    Scaffold(
        containerColor = AsBg,
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        if (uiState.profileStatus is EventStatus.Loading) {
            CircularProgressIndicator()
        } else {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(23.dp)
            ) {
                Text(
                    modifier = Modifier.padding(top = 47.dp, start = 4.dp),
                    text = "프로필",
                    style = MaterialTheme.typography.titleMedium.copy(color = AsWhite)
                )

                TextLabel(
                    modifier = Modifier.padding(top = 68.dp),
                    labelText = "아이디",
                    text = uiState.profileContent?.textId.toString()
                )

                TextLabel(
                    modifier = Modifier.padding(top = 26.dp),
                    labelText = "이름",
                    text = uiState.profileContent?.textName.toString()
                )

                TextLabel(
                    modifier = Modifier.padding(top = 26.dp),
                    labelText = "이메일",
                    text = uiState.profileContent?.textEmail.toString()
                )

                TextLabel(
                    modifier = Modifier.padding(top = 26.dp),
                    labelText = "나이",
                    text = uiState.profileContent?.textAge.toString()
                )

                TextLabel(
                    modifier = Modifier.padding(top = 26.dp),
                    labelText = "파트",
                    text = uiState.profileContent?.textPart.toString()
                )

                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 26.dp),
                    text = "다른 유저들 보러가기",
                    onClick = { onSeeingOtherUsers() },
                )
            }
        }
    }
}

@Composable
fun TextLabel(modifier: Modifier, labelText: String, text: String) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = labelText,
            style = MaterialTheme.typography.bodyMedium.copy(AsWhite)
        )
        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = text,
            style = MaterialTheme.typography.labelSmall.copy(AsSecondaryText)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    LETSSOPTTheme {
        ProfileScreen(
            uiState = ProfileUiState(
                profileContent = ProfileContentItem(
                    textId = "",
                    textName = "",
                    textEmail = "",
                    textAge = 0,
                    textPart = ""
                )
            )
        )
    }
}
