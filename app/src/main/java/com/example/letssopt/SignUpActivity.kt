package com.example.letssopt

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.example.letssopt.ui.components.AsButton
import com.example.letssopt.ui.components.AsTextField
import com.example.letssopt.ui.theme.*
import com.example.letssopt.ui.theme.LETSSOPTTheme
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LETSSOPTTheme {
                val scope = rememberCoroutineScope()
                val snackbarHostState = remember { SnackbarHostState() }

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
                    } },
                    modifier = Modifier.fillMaxSize()) { innerPadding ->
                        SignupContent(
                            modifier = Modifier.padding(innerPadding)
                                .consumeWindowInsets(innerPadding),
                            onShowSnack = { message ->
                                scope.launch {
                                    snackbarHostState.showSnackbar(message)
                                }
                            }
                        )
                }
            }
        }
    }
}

@Composable
fun SignupContent(
    modifier: Modifier = Modifier,
    onShowSnack: (String) -> Unit
) {
    val context = LocalContext.current
    val intent = Intent(context, LoginActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    }
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    var textId by remember { mutableStateOf("") }
    var textPw by remember { mutableStateOf("") }
    var textPwCheck by remember { mutableStateOf("") }

    //전반적인 레이아웃
    Column(modifier = modifier
        .fillMaxSize()
        .background(color = AsBg)
        .padding(20.dp)
        .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        //위쪽 컴포넌트 정렬을 위한 2차 레이아웃 wrapper
        Column(modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .weight(1f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
            ) {
            //로고
            Text(
                modifier = Modifier.padding(top = 40.dp).align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 36.sp ,color = AsPrimary),
                text = "watcha"
            )

            Text(
                modifier = Modifier.padding(top = 20.dp),
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
                text = "회원가입",
                color = AsWhite
            )
            //텍스트필드 섹션
            Text(
                modifier = Modifier.padding(top = 28.dp),
                style = MaterialTheme.typography.labelSmall,
                text = "이메일",
                color = AsSecondaryText
            )

            AsTextField(
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                text = textId,
                onValueChange = { newText ->
                    textId = newText
                },
                hint = "이메일 주소를 입력하세요",
                visible = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )
            Text(style = MaterialTheme.typography.labelSmall,
                text = "비밀번호",
                color = AsSecondaryText,
                modifier = Modifier.padding(top = 12.dp)
            )

            AsTextField(
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                text = textPw,
                onValueChange = { newText ->
                    textPw = newText
                },
                hint = "비밀번호를 입력하세요",
                visible = false,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )

            Text(
                modifier = Modifier.padding(top = 12.dp),
                style = MaterialTheme.typography.labelSmall,
                text = "비밀번호 확인",
                color = AsSecondaryText
            )

            AsTextField(
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                text = textPwCheck,
                onValueChange = { newText ->
                    textPwCheck = newText
                },
                hint = "비밀번호를 다시 입력하세요",
                visible = false,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                )
            )
        }
        // 이쪽 부분에선 래핑해줬던 레이아웃 제거
        AsButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, top = 20.dp),
            "회원가입",
            onClick = {
                if(Patterns.EMAIL_ADDRESS.matcher(textId).matches() &&
                    textPw.length >= 8 && textPw.length <= 12 &&
                    textPw == textPwCheck) {

                    Toast.makeText(context, "회원가입 성공!", Toast.LENGTH_SHORT).show()

                    intent.putExtra("id", textId)
                    intent.putExtra("pw", textPw)
                    context.startActivity(intent)
                } else {
                    onShowSnack("회원가입에 실패했습니다. 올바른 정보를 입력해주세요.")
                }

        },
            enabled =
                if(textId.isNotEmpty() &&
                    textPw.isNotEmpty() &&
                    textPwCheck.isNotEmpty()) true
                else false
        )
    }

}


@Preview(showBackground = true)
@Composable
private fun SignupContentPreview() {
    LETSSOPTTheme {
        SignupContent {

        }
    }
}