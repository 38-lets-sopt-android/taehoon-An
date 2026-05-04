package com.example.letssopt.ui.login

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.data.local.model.AccountItem
import com.example.letssopt.ui.components.DefaultButton
import com.example.letssopt.ui.components.DefaultTextField
import com.example.letssopt.ui.theme.AsBg
import com.example.letssopt.ui.theme.AsPrimary
import com.example.letssopt.ui.theme.AsSecondaryText
import com.example.letssopt.ui.theme.AsWhite
import com.example.letssopt.ui.theme.LETSSOPTTheme
import kotlinx.serialization.Serializable

@Serializable
data object LOGIN

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(),
    navigateToMain: () -> Unit = {},
    navigateToSignUp: () -> Unit = {}
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()
    val interactionSource = remember { MutableInteractionSource() }
    //value로 직접 접근해주면 값을 못읽기에 collectAsStateWithLifecycle()을 통해 추적 가능한 관찰 객체를 만들어줌
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        //전반적인 레이아웃
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = AsBg)
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
                .padding(20.dp)
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //위쪽 컴포넌트 정렬을 위한 2차 레이아웃 wrapper
            Column(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(scrollState),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                //로고
                Text(
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 36.sp, color = AsPrimary),
                    text = "watcha",
                    color = AsPrimary
                )

                Text(
                    modifier = Modifier
                        .padding(top = 20.dp),
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
                    text = "이메일로 로그인",
                    color = AsWhite
                )
                //텍스트필드 섹션
                Text(
                    modifier = Modifier
                        .padding(top = 28.dp),
                    style = MaterialTheme.typography.labelSmall,
                    text = "이메일",
                    color = AsSecondaryText
                )

                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    text = uiState.textId,
                    onValueChange = { viewModel.onChangedId(it) },
                    hint = "이메일 주소를 입력하세요",
                    tfVisible = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    )
                )

                Text(
                    modifier = Modifier
                        .padding(top = 12.dp),
                    style = MaterialTheme.typography.labelSmall,
                    text = "비밀번호",
                    color = AsSecondaryText
                )

                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    text = uiState.textPw,
                    onValueChange = { viewModel.onChangedPw(it) },
                    hint = "비밀번호를 입력하세요",
                    tfVisible = false,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() }
                    )
                )
            }

            //여기 두번쨰 컬럼에서 지정하지도 않은 padding이 양쪽 너비에 들어가서 로그인 버튼이 이상한 패딩이 적용됨
            Column(modifier = Modifier
                .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {

                Text(modifier = Modifier
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = navigateToSignUp
                    )
                    .padding(bottom = 10.dp, top = 10.dp),
                    style = MaterialTheme.typography.labelSmall,
                    color = AsSecondaryText,
                    text = "아직 계정이 없으신가요? 회원가입"

                )

                DefaultButton(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                    text = "로그인",
                    onClick = {
                        validateLogin(
                            context = context,
                            account = viewModel.onGetAccount(),
                            textId = uiState.textId,
                            textPw = uiState.textPw,
                            navigateToMain = navigateToMain,
                            viewModel = viewModel
                        )
                    }
                )
            }
        }
    }
}

private fun validateLogin(
    context: Context,
    account: AccountItem,
    textId: String,
    textPw: String,
    navigateToMain: () -> Unit,
    viewModel: LoginViewModel
) {
    if (account.accountId == null || account.accountPw == null) {
        Toast.makeText(context, "회원가입이 필요합니다.", Toast.LENGTH_LONG).show()
        return
    }
    if (account.accountId != textId) {
        Toast.makeText(context, "존재하지 않는 계정입니다.", Toast.LENGTH_SHORT).show()
        return
    }
    if (account.accountPw != textPw) {
        Toast.makeText(context, "비밀번호가 틀렸습니다. 다시 확인해주세요.", Toast.LENGTH_SHORT).show()
        return
    }
    viewModel.setIsLoggedIn(true)
    navigateToMain()
    Toast.makeText(context, "로그인 성공. 환영합니다. ${account.accountId} 님.", Toast.LENGTH_SHORT).show()
}

@Preview(showBackground = true)
@Composable
private fun LoginContentPreview() {
    LETSSOPTTheme {
        LoginScreen()
    }
}
