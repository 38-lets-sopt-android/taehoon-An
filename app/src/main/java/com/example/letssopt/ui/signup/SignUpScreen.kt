package com.example.letssopt.ui.signup

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.letssopt.ui.components.DefaultButton
import com.example.letssopt.ui.components.DefaultTextField
import com.example.letssopt.ui.theme.AsBg
import com.example.letssopt.ui.theme.AsPrimary
import com.example.letssopt.ui.theme.AsSecondaryText
import com.example.letssopt.ui.theme.AsSurface
import com.example.letssopt.ui.theme.AsWhite
import com.example.letssopt.ui.theme.LETSSOPTTheme
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
data object SignUp

@Composable
fun SignUpRoute(viewModel: SignUpViewModel = viewModel(), onNavigateUp: () -> Unit) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is SignUpSideEffect.ShowToast -> {
                    Toast.makeText(context, effect.message, effect.duration).show()
                }

                is SignUpSideEffect.CompleteSignUp -> {
                    onNavigateUp()
                }

                is SignUpSideEffect.ShowSnack -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(effect.message)
                    }
                }
            }
        }
    }

    SignUpScreen(
        uiState = uiState, // 임시
        snackbarHostState = snackbarHostState,
        onIdChange = { id -> viewModel.onChangedId(id) },
        onPwChange = { pw -> viewModel.onChangedPw(pw) },
        onCkPwChange = { ckPw -> viewModel.onChangedCkPw(ckPw) },
        onNameChange = { name -> viewModel.onChangedName(name) },
        onEmailChange = { email -> viewModel.onChangedEmail(email) },
        onAgeChange = { age -> viewModel.onChangedAge(age) },
        onPartChange = { part -> viewModel.onChangedPart(part) },
        onSignUpClick = { viewModel.validateSignUp()}
    )

}

@Composable
fun SignUpScreen(
    uiState: SignUpUiState,
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    onIdChange: (String) -> Unit,
    onPwChange: (String) -> Unit,
    onCkPwChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onAgeChange: (String) -> Unit,
    onPartChange: (String) -> Unit,
    onSignUpClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                modifier = Modifier
                    .navigationBarsPadding()
                    .imePadding(),
                hostState = snackbarHostState
            ) {
                Snackbar(
                    snackbarData = it,
                    containerColor = AsSurface,
                    contentColor = AsWhite
                )
            }
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        //전반적인 레이아웃
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = AsBg)
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
                .padding(20.dp)
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //위쪽 컴포넌트 정렬을 위한 2차 레이아웃 wrapper
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
                    .weight(1f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                //로고
                Text(
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 36.sp,
                        color = AsPrimary
                    ),
                    text = "watcha"
                )

                Text(
                    modifier = Modifier
                        .padding(top = 20.dp),
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
                    text = "회원가입",
                    color = AsWhite
                )
                //아이디 섹션
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
                    onValueChange = { onIdChange(it) },
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

                // 비밀번호 섹션
                Text(
                    style = MaterialTheme.typography.labelSmall,
                    text = "비밀번호",
                    color = AsSecondaryText,
                    modifier = Modifier.padding(top = 12.dp)
                )

                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    text = uiState.textPw,
                    onValueChange = { onPwChange(it) },
                    hint = "비밀번호를 입력하세요",
                    tfVisible = false,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    )
                )

                // 비밀번호 체크 섹션
                Text(
                    modifier = Modifier
                        .padding(top = 12.dp),
                    style = MaterialTheme.typography.labelSmall,
                    text = "비밀번호 확인",
                    color = AsSecondaryText
                )

                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    text = uiState.textCkPw,
                    onValueChange = { onCkPwChange(it) },
                    hint = "비밀번호를 다시 입력하세요",
                    tfVisible = false,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() }
                    )
                )

                // 이름 섹션
                Text(
                    modifier = Modifier
                        .padding(top = 12.dp),
                    style = MaterialTheme.typography.labelSmall,
                    text = "이름",
                    color = AsSecondaryText
                )

                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    text = uiState.textName,
                    onValueChange = { onNameChange(it) },
                    hint = "이름을 입력하세요",
                    tfVisible = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    )
                )

                // 이메일 섹션
                Text(
                    modifier = Modifier
                        .padding(top = 12.dp),
                    style = MaterialTheme.typography.labelSmall,
                    text = "이메일",
                    color = AsSecondaryText
                )

                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    text = uiState.textEmail,
                    onValueChange = { onEmailChange(it) },
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

                // 나이 섹션
                Text(
                    modifier = Modifier
                        .padding(top = 12.dp),
                    style = MaterialTheme.typography.labelSmall,
                    text = "나이",
                    color = AsSecondaryText
                )

                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    text = uiState.textAge,
                    onValueChange = { onAgeChange(it) },
                    hint = "나이를 입력하세요",
                    tfVisible = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    )
                )

                // 파트 섹션
                Text(
                    modifier = Modifier
                        .padding(top = 12.dp),
                    style = MaterialTheme.typography.labelSmall,
                    text = "파트",
                    color = AsSecondaryText
                )

                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    text = uiState.textPart,
                    onValueChange = { onPartChange(it) },
                    hint = "파트를 입력하세요(ex. 안드로이드, IOS..)",
                    tfVisible = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    )
                )
            }
            if (uiState.signUpStatus is SignUpStatus.Loading) {
                CircularProgressIndicator()
            } else {
                // 이쪽 부분에선 래핑해줬던 레이아웃 제거
                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp, top = 20.dp),
                    "회원가입",
                    onClick = { onSignUpClick() },
                    btEnabled = uiState.textId.isNotEmpty() &&
                            uiState.textPw.isNotEmpty() &&
                            uiState.textCkPw.isNotEmpty() &&
                            uiState.textName.isNotEmpty() &&
                            uiState.textEmail.isNotEmpty() &&
                            uiState.textAge.isNotEmpty() &&
                            uiState.textPart.isNotEmpty()
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun SignUpContentPreview() {
    LETSSOPTTheme {
        SignUpScreen(
            uiState = SignUpUiState(
                textId = "test@sopt.org",
                textName = "안태훈"
            ),
            snackbarHostState = remember { SnackbarHostState() },
            onIdChange = {},
            onPwChange = {},
            onCkPwChange = {},
            onNameChange = {},
            onEmailChange = {},
            onAgeChange = {},
            onPartChange = {},
            onSignUpClick = {}
        )
    }
}
